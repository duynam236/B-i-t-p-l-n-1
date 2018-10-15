package btl1;

import java.util.ArrayList;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.File;

class Word {
    private String word_target;
    private String word_explain;

    public void setWord_target(String word) {
        this.word_target = word;
    }

    public String getWord_target() {
        return word_target;
    }

    public void setWord_explain(String word) {
        this.word_explain = word;
    }

    public String getWord_explain() {
        return word_explain;
    }

    public Word(String word_tar, String word_exp) {
        this.word_target = word_tar;
        this.word_explain = word_exp;
    }
}

class Dictionary {
    ArrayList <Word> Words = new ArrayList();
}

class DictionaryManagement {
    Dictionary A = new Dictionary();
    Dictionary B = new Dictionary();
    // Phien ban tu dien so khai
    public void insertFromCommandline() {
        Scanner Sc = new Scanner(System.in);
        System.out.println("Nhap so tu: ");
        int n = Sc.nextInt();
        for (int i=1;i<=n;i++) {
            System.out.println("Nhap tu tieng anh: ");
            String Eword = Sc.nextLine();
            System.out.println("Nhap tu tieng viet: ");
            String Vword = Sc.nextLine();
            Word newWord = new Word(Eword, Vword);
            A.Words.add(newWord);
        }
    }

    //Ham lay du lieu tu dien tu file
    public void insertFromFile() {

        try (Scanner Sc = new Scanner("C:/Dictionary1.txt")) {
            String Str0,Str1;
            while (Sc.hasNext()) {
                Str0 = Sc.next();
                Str1 = Sc.nextLine();
                Word newWord = new Word(Str0, Str1);
                B.Words.add(newWord);
            }
        }
        catch (Exception e) {

        }
    }

    //Ham tra cuu tu
    public void dictionaryLookup() {
        Scanner Sc = new Scanner(System.in);
        System.out.println("Nhap tu can tra: ");
        String wordlookup = Sc.nextLine();
        boolean Check=false;
        for (int i=0;i<B.Words.size();i++) {
            if (B.Words.get(i).getWord_target().compareTo(wordlookup)==0) {
                System.out.println("Nghia cua tu can tra: " + B.Words.get(i).getWord_explain());
                Check=true;
                break;
            }
        }
        if (Check==false)
            System.out.println("Khong tim thay tu!");
    }

    //Ham them tu
    public void addWord() {
        Scanner Sc = new Scanner(System.in);
        System.out.println("Nhap so tu can them: ");
        int temp = Sc.nextInt();
        for (int i=0;i<temp;i++) {
            System.out.println("Nhap tu can them: ");
            String newTarge = Sc.nextLine();
            System.out.println("Nhap nghia cua tu: ");
            String newExplain = Sc.nextLine();
            Word newWord = new Word(newTarge, newExplain);
            B.Words.add(newWord);
        }
    }

    //Ham sua tu
    public void editWord() {
        Scanner Sc = new Scanner(System.in);
        System.out.println("Nhap tu can sua: ");
        String tempWord = Sc.nextLine();
        int n = B.Words.size(),j=0;
        for (int i=0;i<n;i++) {
            if (B.Words.get(i).getWord_target().compareTo(tempWord)==0) {
                B.Words.remove(B.Words.get(i));
                j=i;
                break;
            }
        }
        System.out.println("Nhap tu can sua: ");
        String newTarge = Sc.nextLine();
        System.out.println("Nhap nghia cua tu: ");
        String newExplain = Sc.nextLine();
        Word newWord = new Word(newTarge, newExplain);
        B.Words.add(j, newWord);
    }

    //Ham xoa tu
    public void deleteWord() {
        Scanner Sc = new Scanner(System.in);
        System.out.println("Nhap tu can xoa: ");
        String tempWord = Sc.nextLine();
        int n = B.Words.size();
        for (int i=0;i<n;i++) {
            if (B.Words.get(i).getWord_target().compareTo(tempWord)==0) {
                B.Words.remove(B.Words.get(i));
                break;
            }
        }
    }

    //Ham ghi du lieu tu dien ra file
    public void dictionaryExportToFile() {
        Scanner Sc = new Scanner(System.in);
        int n = B.Words.size();
        System.out.println("Nhap ten file can ghi: ");
        String Text = Sc.nextLine();
        try (FileWriter Fw = new FileWriter(new File(Text))) {
            for (int i=0;i<n;i++) {
                Fw.write(B.Words.get(i).getWord_target() + "\t" + B.Words.get(i).getWord_explain() + "\n");
            }
            Fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}

class DictionaryCommandLine extends DictionaryManagement {

    public void showAllWords() {
        int n = B.Words.size();
        System.out.println("No          |English            |Vietnamese" + "\n");
        for (int i=0;i<n;i++) {
            System.out.println(i+1  + "            |" + B.Words.get(i).getWord_target() + "            |" + B.Words.get(i).getWord_explain() +"\n");
        }
    }

    public void dictionaryBasic() {
        this.insertFromCommandline();
        this.showAllWords();
    }

    public void dictionaryAdvanced() {
        insertFromFile();
        showAllWords();
        dictionaryAdvanced();
    }

    public void dictionarySearcher() {

    }

}

public class BTL1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
}
