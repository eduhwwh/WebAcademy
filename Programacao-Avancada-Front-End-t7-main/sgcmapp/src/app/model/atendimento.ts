import { Convenio } from "./convenio";
import { Paciente } from "./paciente";
import { Profissional } from "./profissional";

export type Atendimento = {
    id: number;
    data: string;
    hora: string;
    status: string;
    convenio_id: number;
    convenio_nome: string;
    paciente_id: number;
    paciente_nome: string;
    profissional_id: number;
    profissional_nome: string;
    unidade_nome: string;
}
