describe('Agenda', () => {

  beforeEach(() =>{
    cy.intercept('GET', '/atendimento/consultar?status=AGENDADO,CONFIRMADO',{
      statusCode: 200,
      fixture: 'atendimentos.json'
    })
    cy.visit('/');
    cy.get('nav a').contains('Agenda').click();
  });



  it('deve criar novo agendamento', () => {
    cy.intercept('GET', '/convenio/ativos',{
      statusCode:200,
      fixture: 'convenios.json'
    });

    cy.intercept('GET', '/profissional/consultar',{
      statusCode:200,
      fixture: 'profissionais.json'
    });

    cy.intercept('GET', '/paciente/consultar',{
      statusCode:200,
      fixture: 'pacientes.json'
    });

    cy.intercept('POST', '/atendimento/inserir',{
      statusCode:201,
      body:'6'
    });

    cy.get('a#add').click();

    cy.get('select#profissional').select('Davi Jesus Mendes');
    cy.get('select#paciente').select('Helen Dutra Vilar');
    cy.get('input#data').type('2025-09-01');
    cy.get('select#hora').select('14:00');
    cy.get('select#convenio').select('Unimed');

    cy.fixture('atendimentos.json').then(atendimentos =>{
      cy.fixture('atendimento-inserir.json').then(novoAtendimento =>{
        atendimentos.push(novoAtendimento);
        cy.intercept('GET', '/atendimento/consultar?status=AGENDADO,CONFIRMADO',{
          statusCode: 200,
          body: atendimentos
        })
      })
    })

    const mensagens: string[] = [];
    cy.on('window:alert', mensagem => mensagens.push(mensagem))

    cy.get('input[type="submit"]').click();

    cy.wrap(mensagens).should('have.length', 2).then(() => {
      expect(mensagens[0]).to.contain('ID gerado: 6');
      expect(mensagens[1]).to.contain('Operação realizada com sucesso');
    })

    cy.get('table tbody tr:last').within(() =>{
      cy.get('td').eq(0).contains('01/09/2025');
      cy.get('td').eq(1).contains('14:00');
      cy.get('td').eq(2).contains('Helen Dutra Vilar');
      cy.get('td').eq(3).contains('Davi Jesus Mendes');
      cy.get('td').eq(5).contains('Unimed');
    });
  });
});