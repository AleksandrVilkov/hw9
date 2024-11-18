package org.example;


import java.io.*;

public class HomeWork {

    /**
     * <h1>Задание 1.</h1>
     * Решить задачу UPIT из файла contest7_tasks.pdf
     */
    public void upit(InputStream in, OutputStream out) {
        var dis = new DataInputStream(in);
        var sequenceLength = readInt(dis);
        var operationCount = readInt(dis);
        var sequence = getSequence(dis, sequenceLength);
        print(out, dis, operationCount, sequence);
    }

    private long[] getSequence(DataInputStream dis, int sequenceLength) {
        var sequence = new long[sequenceLength];
        for (int i = 0; i < sequenceLength; i++)
            sequence[i] = readInt(dis);
        return sequence;
    }

    private void print(OutputStream out, DataInputStream dis, int operationCount, long[] sequence) {
        var ps = new PrintStream(out);
        for (int i = 0; i < operationCount; i++) {
            int operation = readInt(dis);
            switch (operation) {
                case 1:
                    update(sequence, dis);
                    break;
                case 2:
                    add(sequence, dis);
                    break;
                case 3:
                    sequence = insert(sequence, dis);
                    break;
                case 4:
                    ps.println(sum(sequence, dis));
                    break;
            }
        }
        ps.close();
    }

    //Первый тип операции
    private void update(long[] sequence, DataInputStream in) {
        int a = readInt(in) - 1;
        int b = readInt(in) - 1;
        long c = readInt(in);
        for (int j = a; j <= b; j++)
            sequence[j] = c;
    }

    //Второй тип операции
    private void add(long[] sequence, DataInputStream in) {
        int a = readInt(in) - 1;
        int b = readInt(in) - 1;
        long x = readInt(in);
        for (int j = a; j <= b; j++) {
            sequence[j] += (j - a + 1) * x;
        }
    }

    //Третий тип операции
    private long[] insert(long[] sequence, DataInputStream in) {
        int a = readInt(in) - 1;
        long b = readInt(in);
        long[] newSequence = new long[sequence.length + 1];
        if (a >= 0)
            System.arraycopy(sequence, 0, newSequence, 0, a);
        newSequence[a] = b;
        if (sequence.length - a >= 0)
            System.arraycopy(sequence, a, newSequence, a + 1, sequence.length - a);
        return newSequence;
    }

    //Четвертый тип операции
    private long sum(long[] sequence, DataInputStream in) {
        var a = readInt(in) - 1;
        var b = readInt(in) - 1;
        var sum = 0L;
        for (int j = a; j <= b; j++)
            sum += sequence[j];

        return sum;
    }

    private int readInt(DataInputStream in) {
        StringBuilder sb = new StringBuilder();
        int b;
        try {
            while ((b = in.read()) != -1 && b != ' ' && b != '\n')
                sb.append((char) b);
        } catch (IOException e) {
            //Do nothing
        }
        return Integer.parseInt(sb.toString().trim());
    }
}
