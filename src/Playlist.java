public class Playlist {
    private class NoMusica {
        Musica musica;
        NoMusica proximo;
        NoMusica anterior;

        NoMusica(Musica musica) {
            this.musica = musica;
        }
    }

    private NoMusica inicio;
    private NoMusica fim;
    private NoMusica atual;

    public void adicionarMusicaInicio(Musica musica) {
        NoMusica novoNo = new NoMusica(musica);
        if (inicio == null) {
            inicio = fim = atual = novoNo;
        } else {
            novoNo.proximo = inicio;
            inicio.anterior = novoNo;
            inicio = novoNo;
        }
    }

    public void adicionarMusicaFim(Musica musica) {
        NoMusica novoNo = new NoMusica(musica);
        if (inicio == null) {
            inicio = fim = atual = novoNo;
        } else {
            fim.proximo = novoNo;
            novoNo.anterior = fim;
            fim = novoNo;
        }
    }

    public void adicionarMusicaPosicao(Musica musica, int posicao) {
        if (posicao <= 0) {
            adicionarMusicaInicio(musica);
        } else {
            NoMusica novoNo = new NoMusica(musica);
            NoMusica noAtual = inicio;
            int indice = 0;

            while (noAtual != null && indice < posicao - 1) {
                noAtual = noAtual.proximo;
                indice++;
            }

            if (noAtual == null) {
                adicionarMusicaFim(musica);
            } else {
                novoNo.proximo = noAtual.proximo;
                novoNo.anterior = noAtual;
                if (noAtual.proximo != null) {
                    noAtual.proximo.anterior = novoNo;
                }
                noAtual.proximo = novoNo;
                if (novoNo.proximo == null) fim = novoNo;
            }
        }
    }

    public void removerMusicaPorTitulo(String titulo) {
        NoMusica noAtual = inicio;
        while (noAtual != null) {
            if (noAtual.musica.getTitulo().equalsIgnoreCase(titulo)) {
                if (noAtual.anterior != null) {
                    noAtual.anterior.proximo = noAtual.proximo;
                } else {
                    inicio = noAtual.proximo;
                }
                if (noAtual.proximo != null) {
                    noAtual.proximo.anterior = noAtual.anterior;
                } else {
                    fim = noAtual.anterior;
                }
                System.out.println("Música removida: " + titulo);
                return;
            }
            noAtual = noAtual.proximo;
        }
        System.out.println("Música não encontrada: " + titulo);
    }

    public void listarMusicas() {
        NoMusica noAtual = inicio;
        while (noAtual != null) {
            System.out.println(noAtual.musica);
            noAtual = noAtual.proximo;
        }
    }

    public void ordenarPorTitulo() {
        ordenar((m1, m2) -> m1.getTitulo().compareToIgnoreCase(m2.getTitulo()));
    }

    public void ordenarPorArtista() {
        ordenar((m1, m2) -> m1.getArtista().compareToIgnoreCase(m2.getArtista()));
    }

    private void ordenar(java.util.Comparator<Musica> comparator) {
        if (inicio == null || inicio.proximo == null) return;
        boolean trocou;
        do {
            trocou = false;
            NoMusica atual = inicio;
            while (atual.proximo != null) {
                if (comparator.compare(atual.musica, atual.proximo.musica) > 0) {
                    Musica temp = atual.musica;
                    atual.musica = atual.proximo.musica;
                    atual.proximo.musica = temp;
                    trocou = true;
                }
                atual = atual.proximo;
            }
        } while (trocou);
    }

    public void proximaMusica() {
        if (atual != null && atual.proximo != null) {
            atual = atual.proximo;
            System.out.println("Tocando: " + atual.musica);
        } else {
            System.out.println("Já está na última música.");
        }
    }

    public void musicaAnterior() {
        if (atual != null && atual.anterior != null) {
            atual = atual.anterior;
            System.out.println("Tocando: " + atual.musica);
        } else {
            System.out.println("Já está na primeira música.");
        }
    }

    public void exibirMusicaAtual() {
        if (atual != null) {
            System.out.println("Tocando agora: " + atual.musica);
        } else {
            System.out.println("Nenhuma música está tocando no momento.");
        }
    }
}
