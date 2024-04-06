//algoritimo guloso 

/**
 * Uma empresa quer implementar um novo sistema de backup no qual arquivos sao
 * guardados em fitas de dados.
 * 
 * O novo sistema deve seguir as duas seguintes regras:
 * 
 * 1- Numca colocar mais de dois arquivos na mesma fita .
 * 2- Os arquivos não podem ser divididos entre múltiplas fitas.
 * 
 * É garantido que todas as fitas tenham o mesmo tamanho o que elas sempre serão
 * mcapazes de guardar o maior arquivo.
 * Toda vez que este processo for executado, nós já saberemos o tamanho de cada
 * arquivo e a capacidade das fitas.
 * Com isso em mente, desenhe uma solução que seja capaz de contar quantos fitas
 * serão requweridas para guardar o backup de forma mais eficiente.
 * O parâmetro de sua função será uma estrutura que conterá o tamanho dos
 * arquivos e a capacidade dad fitas.
 * Você deve retornar a quantidade mínima de fitas requeridas para guardar os
 * arquivos.
 * 
 * Exemplo:
 * 
 * //Fita 1 : 70 + 20
 * //Fita 2 : 10
 * Input: Tape size = 100; Files: 70, 10, 20
 * Output: 2;
 * 
 */

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class BackupSystem {

    public interface Batch {

        int[] getFileSize();

        int getTapesize();
    }

    private static class NewBatch implements Batch {

        @Override
        public int[] getFileSize() {
            return new int[] {
                    70, 10, 20, 40, 50, 60, 50, 100, 100 // 100, 100, 60, 50, 40, 20, 10
                    // 6 = numeros de fitar com size de 100
                    // 100 = fita 1
                    // 100 = fita 2
                    // 70, 20 = fita 3
                    // 60, 40 = fita 4
                    // 50, 50 = fita 5
                    // 10 = fita 6
            };
        }

        @Override
        public int getTapesize() {
            return 100;
        }

    }

    private class Tape {
        private int stored;
        private int numberOfFiles;

        public Tape(int stored) {
            this.stored = stored;
            this.numberOfFiles = 1;
        }

        public int getStored() {
            return stored;
        }

        public void setStored(int stored) {
            this.stored = stored;
        }

        public int getNumberOfFiles() {
            return numberOfFiles;
        }

        public void setNumberOfFiles(int numberOfFiles) {
            this.numberOfFiles = numberOfFiles;
        }

        public void addStorage(int storage) {
            this.stored += storage;
        }

        public void addFiles() {
            this.numberOfFiles++;
        }

    }

    public int getMinimunTapeCount(final Batch bath) {
        // Primeiro, ordenamos o array de tamanhos de arquivos.
        int[] sizes = bath.getFileSize();
        Arrays.sort(sizes);

        int tapes = 0;
        List<Tape> list = new ArrayList<Tape>();

        for (int i = sizes.length - 1; i > -1; i--) {
            boolean stored = false;

            for (Tape tape : list) {
                if (((tape.getStored() + sizes[i]) <= bath.getTapesize()) && (tape.getNumberOfFiles() < 2)) {
                    tape.addStorage(sizes[i]);
                    tape.addFiles();
                    stored = true;
                    break;
                }
            }

            if (!stored) {
                tapes++;
                list.add(new Tape(sizes[i]));
            }
        }

        return tapes;
    }

    public static void main(String[] args) throws Exception {

        BackupSystem backupSystem = new BackupSystem();
        Batch batch = new NewBatch();

        System.out.println(backupSystem.getMinimunTapeCount(batch));
    }
}
