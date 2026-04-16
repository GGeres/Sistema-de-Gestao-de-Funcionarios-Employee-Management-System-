package edu.curso;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class GestaoFuncionarios {
    private int indice = 0;
    private Funcionario[] funcionarios = new Funcionario[50];
    private Scanner scan = new Scanner(System.in);
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public void criar(){
        if(indice < 50){
            Funcionario f = new Funcionario();
            System.out.print("Digite o ID: ");
            f.setId(Long.parseLong(scan.nextLine()));
            System.out.print("Nome: ");
            f.setNome(scan.nextLine());
            System.out.print("Matricula: ");
            f.setMatricula(scan.nextLine());
            System.out.print("Salario: ");
            f.setSalario(Float.parseFloat(scan.nextLine()));
            System.out.print("Horario: ");
            f.setHorario(scan.nextLine());

            try {
                System.out.print("Data de Admissao (dd/mm/aaaa): ");
                f.setAdmissao(sdf.parse(scan.nextLine()));
            } catch (Exception e) {
                System.out.println("Invalid Date!");
            }

            funcionarios[indice++] = f;
            System.out.println("Funcionario cadastrado!");
        } else{
            System.out.println("Limite de 50 funcionario atingido");
        }
    }

    public void exibir(){
        System.out.print("Digite a matricula para busca: ");
        String matSearch = scan.nextLine();
        boolean found = false;

        for(int i = 0; i < indice; i++){
            if(funcionarios[i] != null && funcionarios[i].getMatricula().equals(matSearch)){
                funcionarios[i].exibir();
                found = true;
            }
        }
        if(!found) System.out.println("Nenhum funcionario com essa matricula!");
    }

    public void excluir(){
        System.out.println("Digite a matricula para excluir: ");
        String matSearch = scan.nextLine();
        boolean removed = false;

        for(int i = 0; i < indice; i++){
            if(funcionarios[i] != null && funcionarios[i].getMatricula().equals(matSearch)){
                for(int j = i; j < indice - 1; j++){
                    funcionarios[j] = funcionarios[j + 1];
                }
                funcionarios[--indice] = null;
                removed = true;
                i--;
            }
        }
        if(removed) System.out.println("Funcionario removido.");
        else System.out.println("Matricula não encontrada.");
    }   

    public void atualizar(){
        System.out.print("Digite a matricula para atualizar: ");
        String matSearch = scan.nextLine();

        for(int i = 0; i < indice; i++){
            if(funcionarios[i]!= null && funcionarios[i].getMatricula().equals(matSearch)){
                System.out.print("Novo Nome: ");
                funcionarios[i].setNome(scan.nextLine());
                System.out.print("Novo Salario: ");
                funcionarios[i].setSalario(Float.parseFloat(scan.nextLine()));
                System.out.print("Novo Horario: ");
                funcionarios[i].setHorario(scan.nextLine());

                try {
                    System.out.print("Nova Admissao (dd/mm/aaaa): ");
                    funcionarios[i].setAdmissao(sdf.parse(scan.nextLine()));
                    System.out.print("Data de Demissao (ou enter para nulo): ");
                    String dem = scan.nextLine();
                    if(!dem.isEmpty()) funcionarios[i].setDemissao(sdf.parse(dem));
                } catch (Exception e) {}

                System.out.println("Dados Atualizados.");
                return;
            } 
        }
        System.out.println("Funcionario Not Found.");
    }

    public void menu(){
        while (true) { 
            System.out.println("\n--- GESTAO DE FUNCIONARIOS ---");
            System.out.println("(C)riar  (E)xibir  (R)emover  (A)tualizar  (S)air");
            System.out.print("Options: ");
            String textUpper = scan.nextLine().toUpperCase();
            if(textUpper.isEmpty()){
                continue;
            }
            char letra = textUpper.charAt(0);

            switch(letra){
                case 'C': criar(); break;
                case 'E': exibir(); break;
                case 'R': excluir(); break;
                case 'A': atualizar(); break;
                case 'S': System.exit(0);
                default: System.out.println("Invalid Option.");
            }
        }
    }

    public static void main(String[] args) {
        new GestaoFuncionarios().menu();
    }

}
