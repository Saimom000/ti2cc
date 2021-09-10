package com.ti2cc;

import java.util.*;

public class Principal {
    public static Scanner sc = new Scanner(System.in);

    public static void imprimeMenu() {
        System.out.println("Digite algum numero:");
        System.out.println("1 - Listar:");
        System.out.println("2 - Inserir");
        System.out.println("3 - Excluir");
        System.out.println("4 - Atualizar");
        System.out.println("5 - Sair");
    }

    public static void listar(DAO dao) {
        System.out.println("==== Mostrar usuários === ");
        Usuario[] usuarios = dao.getUsuarios();
        for (int i = 0; i < usuarios.length; i++) {
            System.out.println(usuarios[i].toString());
        }
    }

    public static int inserir(DAO dao, int cod) {
        String login, senha, sexo;
        cod++;
        System.out.println("==== Inserir usuários === ");
        System.out.println("Digite o login:");
        login = sc.next();
        System.out.println("Digite a senha:");
        senha = sc.next();
        System.out.println("Digite o sexo (M ou F):");
        sexo = sc.next();

        Usuario usuario = new Usuario(cod, login, senha, sexo.charAt(0));
        if (dao.inserirUsuario(usuario) == true) {
            System.out.println("Inserção com sucesso -> " + usuario.toString());
        }

        return cod;
    }

    public static void excluir(DAO dao) {
        int cod;
        System.out.println("==== Excluir usuários === ");
        System.out.println("Digite o codigo do usuario:");
        cod = sc.nextInt();
        dao.excluirUsuario(cod);
    }

    public static void menuAtualizar() {
        System.out.println("==== Atualizar dado === ");
        System.out.println("Digite algum numero:");
        System.out.println("1 - Login");
        System.out.println("2 - Senha");
        System.out.println("3 - Sexo");
    }

    public static void atualizar(DAO dao) {
        int cod, opcao;
        String resp;
        System.out.println("==== Atualizar usuários === ");
        System.out.println("Digite o codigo do usuario:");
        cod = sc.nextInt();

        Usuario usuario[] = dao.selecionarUsuario(cod);
        menuAtualizar();

        do {
            opcao = sc.nextInt();
            if (opcao == 1) {
                System.out.println("Digite o novo login:");
                resp = sc.next();
                usuario[0].setLogin(resp);
            } else if (opcao == 2) {
                System.out.println("Digite a nova senha:");
                resp = sc.next();
                usuario[0].setSenha(resp);
            } else if (opcao == 3) {
                System.out.println("Digite o novo sexo:");
                resp = sc.next();
                usuario[0].setSexo(resp.charAt(0));
            } else {
                System.out.println("Numero invalido, digite novamente");
            }
        } while (opcao != 1 && opcao != 2 && opcao != 3);
        dao.atualizarUsuario(usuario[0]);
    }

    public static void main(String[] args) {

        DAO dao = new DAO();

        dao.conectar();

        int x = 1, opcao, cod = 11;

        do {
            if (x == 1) {
                imprimeMenu();
            }
            opcao = sc.nextInt();
            x = 1;
            if (opcao == 1) {// listar
                listar(dao);
            } else if (opcao == 2) {// inserir
                cod = inserir(dao, cod);
            } else if (opcao == 3) {// excluir
                excluir(dao);
            } else if (opcao == 4) {// atualizar
                atualizar(dao);
            } else if (opcao == 5) {// sair
                x = 0;
            } else {
                System.out.println("Valor invalido, digite novamente");
                x = 2;
            }
        } while (x != 0);

        dao.close();
    }
}
