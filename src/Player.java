import java.util.Scanner;

public class Player {
    private Playlist playlist = new Playlist();
    private Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        int opcao;
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir newline

            switch (opcao) {
                case 1:
                    playlist.proximaMusica();
                    break;
                case 2:
                    playlist.musicaAnterior();
                    break;
                case 3:
                    ordenarPlaylist();
                    break;
                case 4:
                    playlist.exibirMusicaAtual();
                    break;
                case 5:
                    adicionarMusica();
                    break;
                case 6:
                    removerMusica();
                    break;
                case 7:
                    playlist.listarMusicas();
                    break;
                case 8:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 8);
    }

    private void exibirMenu() {
        System.out.println("\nBem-vindo ao seu Gerenciador de Músicas!");
        System.out.println("1. Próxima música");
        System.out.println("2. Música anterior");
        System.out.println("3. Ordenar playlist");
        System.out.println("4. Tocar música");
        System.out.println("5. Adicionar música");
        System.out.println("6. Remover música");
        System.out.println("7. Listar músicas");
        System.out.println("8. Sair");
        System.out.print("Digite a opção desejada: ");
    }

    private void ordenarPlaylist() {
        System.out.println("Escolha o critério de ordenação:");
        System.out.println("1. Título");
        System.out.println("2. Artista");
        int opcao = scanner.nextInt();
        if (opcao == 1) {
            playlist.ordenarPorTitulo();
        } else if (opcao == 2) {
            playlist.ordenarPorArtista();
        } else {
            System.out.println("Opção inválida.");
        }
    }

    private void adicionarMusica() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Artista: ");
        String artista = scanner.nextLine();
        System.out.print("Álbum: ");
        String album = scanner.nextLine();
        System.out.print("Duração (segundos): ");
        int duracao = scanner.nextInt();
        scanner.nextLine(); // Consumir newline

        Musica musica = new Musica(titulo, artista, album, duracao);
        System.out.println("Onde deseja adicionar a música?");
        System.out.println("1. No início");
        System.out.println("2. No fim");
        System.out.println("3. Em uma posição específica");
        int posicao = scanner.nextInt();
        scanner.nextLine(); // Consumir newline

        if (posicao == 1) {
            playlist.adicionarMusicaInicio(musica);
        } else if (posicao == 2) {
            playlist.adicionarMusicaFim(musica);
        } else if (posicao == 3) {
            System.out.print("Informe a posição desejada: ");
            int indice = scanner.nextInt();
            playlist.adicionarMusicaPosicao(musica, indice);
        } else {
            System.out.println("Opção inválida. A música será adicionada ao fim.");
            playlist.adicionarMusicaFim(musica);
        }
    }

    private void removerMusica() {
        System.out.print("Informe o título da música para remover: ");
        String titulo = scanner.nextLine();
        playlist.removerMusicaPorTitulo(titulo);
    }
}
