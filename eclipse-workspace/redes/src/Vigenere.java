import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Vigenere {
    String[] abc;
    Map<String, Integer> abcMap;
    String[] codigo;
    String[] chave;

    public Vigenere(int i) {
        abc = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
        abcMap = new HashMap<>();
        for (int j = 0; j < abc.length; j++) {
            abcMap.put(abc[j], j);
        }
        codigo = new String[] { "a", "v", "e", "g", "e", "s", "g", "g", "e", "t", "v", "u", "a", "u", "c", "g", "e", "s", "g", "g", "e", "t", "f", "l","g","j","v","d","i","t" };
        chave = new String[i];
    }

    public String decifrarCodigo(String[] chave) {
        StringBuilder resultado = new StringBuilder();
        for (int n = 0; n < codigo.length; n++) {
            int posCodigo = abcMap.get(codigo[n]);
            int posChave = abcMap.get(chave[n % chave.length]);
            int posDecifrado = (posCodigo - posChave + abc.length) % abc.length;
            resultado.append(abc[posDecifrado]);
        }
        return resultado.toString();
    }

    public void gravarResultado(String texto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("resultado.txt", true))) {
            writer.write(texto);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printProgress(long atual, long total) {
        int progress = (int) ((double) atual / total * 100);
        System.out.printf("\rProgresso: %d%% (%d/%d)", progress, atual, total);
        System.out.flush();
    }

    public void decifrar(int i) {
    	
    	// Limpa o arquivo resultado.txt no início do processamento
    	try (BufferedWriter writer = new BufferedWriter(new FileWriter("resultado.txt"))) {
    	    writer.write(""); // Escreve uma string vazia para limpar o conteúdo
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}

    	
        long totalComb = (long) Math.pow(abc.length, i); // Total de combinações
        long count = 0;

        int[] comb = new int[i];
        StringBuilder buffer = new StringBuilder();

        // Posições da chave já conhecidas (7, 8, 18, 19) com valores 'b', 'c', 'b', 'c'
        Map<Integer, String> posicoesFixas = new HashMap<>();
        posicoesFixas.put(7, "b");
        posicoesFixas.put(8, "c");
        posicoesFixas.put(18, "b");
        posicoesFixas.put(19, "c");

        while (true) {
            for (int j = 0; j < i; j++) {
                
                if (posicoesFixas.containsKey(j)) {
                    chave[j] = posicoesFixas.get(j);
                } else {
                    chave[j] = abc[comb[j]];
                }
            }

            String resultado = decifrarCodigo(chave);
            String chaveStr = String.join("", chave);

//            // Verifica se o texto contém a palavra "redes"
//            if (resultado.contains("redes")) {
//                System.out.println("\nChave encontrada: " + chaveStr);
//                gravarResultado("Chave: " + chaveStr + " -> " + resultado);
//                return; // Interrompe ao encontrar a chave correta
//            }

            buffer.append("Chave: ").append(chaveStr).append(" -> ").append(resultado).append("\n");

            if (count % 1000 == 0) {
                gravarResultado(buffer.toString());
                buffer.setLength(0); // Limpa o buffer
            }

            count++;
            printProgress(count, totalComb);

            // Geração da próxima combinação para posições não fixas
            int k = i - 1;
            while (k >= 0) {
                // Pular posições fixas
                if (posicoesFixas.containsKey(k)) {
                    k--;
                    continue;
                }

                comb[k]++;
                if (comb[k] < abc.length) 
                    break;
                comb[k] = 0;
                k--;
            }
            if (k < 0)
                break;
        }

        // Grava o restante no buffer
        if (buffer.length() > 0) {
            gravarResultado(buffer.toString());
        }

        System.out.println("\nDecifração concluída!");
    }


    public static void main(String[] args) {
    	int tamanho = 4;//a chave certa é 4, as de 6 e de 12 não funcionam(é muita procura)
        Vigenere v = new Vigenere(tamanho); 
        v.decifrar(tamanho);
    }
}
