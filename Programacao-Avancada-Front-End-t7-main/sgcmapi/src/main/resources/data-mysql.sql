SET SESSION FOREIGN_KEY_CHECKS=0;

INSERT INTO `atendimento` (`id`, `data`, `hora`, `status`, `convenio_id`, `paciente_id`, `profissional_id`) VALUES
    (1,'2025-10-01','14:00:00','AGENDADO',1,1,1),
    (2,'2025-10-01','14:30:00','AGENDADO',2,2,2),
    (3,'2025-10-02','14:30:00','AGENDADO',3,3,3),
    (4,'2025-10-02','15:00:00','AGENDADO',1,4,4),
    (5,'2025-10-03','15:00:00','AGENDADO',2,1,1),
    (6,'2025-10-03','16:00:00','AGENDADO',3,2,1),
    (7,'2025-10-06','15:30:00','AGENDADO',1,3,2),
    (8,'2025-10-06','16:30:00','AGENDADO',2,4,2),
    (9,'2025-10-07','17:00:00','AGENDADO',3,5,5),
    (10,'2025-10-07','17:30:00','AGENDADO',1,2,3),
    (11,'2025-10-07','18:00:00','AGENDADO',3,3,1),
    (12,'2025-10-08','17:00:00','AGENDADO',1,3,5),
    (13,'2025-10-08','17:30:00','AGENDADO',1,4,5),
    (14,'2025-10-09','18:00:00','AGENDADO',1,5,5),
    (15,'2025-10-09','18:30:00','AGENDADO',1,1,5),
    (16,'2025-10-10','15:00:00','AGENDADO',1,4,4),
    (17,'2025-10-10','15:00:00','AGENDADO',2,1,1),
    (18,'2025-10-13','16:00:00','AGENDADO',3,2,1),
    (19,'2025-10-13','15:30:00','AGENDADO',1,3,2),
    (20,'2025-10-13','16:30:00','AGENDADO',2,4,2);

INSERT INTO `convenio` (`id`, `ativo`, `cnpj`, `email`, `nome`, `razao_social`, `representante`, `telefone`) VALUES
    (1,b'1','46.560.030/0001-53','contato@unimedriobranco.com.br','Unimed','Unimed Rio Branco Cooperativa de Trabalho Médico Ltda.','Thayline Figueredo Vaz','(68) 3668-1546'),
    (2,b'1','29.309.127/0001-79','contato@amil.com.br','Amil','AMIL ASSISTENCIA MEDICA INTERNACIONAL S.A.','Davi Faria da Conceição','(11) 3279-3035'),
    (3,b'1','92.693.118/0001-60','contato@bradescosaude.com.br','Bradesco','BRADESCO SAUDE S.A.','Leandra Paes dos Anjos','(21) 2503-0787');

INSERT INTO `especialidade` (`id`, `nome`) VALUES
    (1,'Cardiologia'),
    (2,'Dermatologia'),
    (3,'Geriatria'),
    (4,'Infectologia'),
    (5,'Pediatria'),
    (6,'Psiquiatria'),
    (7,'Urologia');

INSERT INTO `paciente` (`id`, `cep`, `cidade`, `data_nascimento`, `email`, `endereco`, `estado`, `grupo_sanguineo`, `nome`, `sexo`, `telefone`) VALUES
    (1,'69903-134','Rio Branco','1945-09-28','giulia.bencatel@gmail.com','Rua Cedro, 100 - Parque dos Sabiás','AC','AB_POSITIVO','Giulia Farias Bencatel','F','(68) 98105-2583'),
    (2,'69901-884','Rio Branco','1941-04-22','wallace.soriano@yahoo.com','Travessa Mossoró, 90 - Vitória','AC','B_POSITIVO','Wallace Macedo Inácio Soriano','M','(68) 97431-7722'),
    (3,'69909-060','Rio Branco','1997-06-05','helen.vilar@gmail.com','Rua Adelaide, 50 - Rosa Linda','AC','AB_NEGATIVO','Helen Dutra Vilar','F','(68) 99752-4954'),
    (4,'69911-262','Rio Branco','2009-03-10','mario.branco@uol.com.br','Rua Luiz Galvez, 200 - Conjunto Castelo Branco','AC','O_POSITIVO','Jean Schuenck Amorin','M','(68) 97120-8079'),
    (5,'69980-970','Cruzeiro do Sul','1974-11-14','lucilene.lucas@gmail.com','Rua Rego Barros, 427 - Centro','AC','A_POSITIVO','Lucilene Santos Lucas','F','(68) 99384-3354');

INSERT INTO `profissional` (`id`, `email`, `nome`, `registro_conselho`, `telefone`, `especialidade_id`, `unidade_id`) VALUES
    (1,'monique.nespoli@uol.com.br','Maria Adelia Serravalle Bezerra','CRM/AC 377','(68) 98205-4704',1,1),
    (2,'elielson.andrade@gmail.com','Elielson Silveira Andrade','CRM/AC 455','(68) 98085-4910',1,1),
    (3,'davi.mendes@yahoo.com','Davi Jesus Mendes','CRM/AC 123','(68) 98408-5352',4,3),
    (4,'carla.valle@gmail.com','Carla da Paixão Valle','CRM/AC 234','(68) 98395-5604',3,1),
    (5,'neuza.nobrega@uol.com.br','Neuza Biango Nobrega','CRM/AC 232','(68) 98561-6622',2,2);

INSERT INTO `unidade` (`id`, `endereco`, `nome`) VALUES
    (1,'Rua Independência, 56 - Conjunto Bela Vista','Bela Vista'),
    (2,'Rua Paulo VI, 100 - Bosque','Bosque'),
    (3,'Rua Rui Barbosa, 252 - Centro','Cruzeiro do Sul');

INSERT INTO `usuario` (`id`, `ativo`, `nome_completo`, `nome_usuario`, `papel`, `senha`) VALUES
    (1,b'1','Administrador','admin','ROLE_ADMIN','$2a$10$h.s3BmgI2/N0MuDYEiawsOYh1P8l7gUcKRY6nhlDqnfcgHTv9Eb7S'),
    (2,b'1','Daniel','daniel','ROLE_ADMIN','$2a$10$8b4pxEEFWXleegyJRhopLO748ONErS6gNpw8MkzldN7KU0jM6hjK.'),
    (3,b'1','Teste','teste','ROLE_ATENDENTE','$2a$10$66Pd4cJ90qXZYPpVk.8ST.vu6OeOPq3/hEy42JHfJnP/QS7xxa5LS');

SET SESSION FOREIGN_KEY_CHECKS=1;
