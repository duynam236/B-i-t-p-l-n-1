package btl1;


import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


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
    // Phien ban tu dien so khai
    public void insertFromCommandline() {
        Scanner Sc = new Scanner(System.in);
        System.out.print("Nhap so tu: ");
        int n = Sc.nextInt();
        for (int i=0;i<n;i++) {
            System.out.print("Nhap tu tieng anh: ");
            String Eword = Sc.nextLine();
            System.out.print("Nhap tu tieng viet: ");
            String Vword = Sc.nextLine();
            Word newWord = new Word(Eword, Vword);
            A.Words.add(newWord);
        }
    }

    //Ham lay du lieu tu dien tu file
    public void insertFromFile(Dictionary A) {
        InputStream stream = BTL1.class.getResourceAsStream("/btl1/Dictionary1.txt");
        try (Scanner scan = new Scanner(stream)) {
            while (scan.hasNext()) {
                String Str0 = scan.next();
                String Str1 = scan.nextLine();
                Word words = new Word(Str0, Str1);
                A.Words.add(words);
            }
        } catch (Exception e) {
            
        }

    }


    //Ham tra cuu tu
    public void dictionaryLookup(Dictionary A) {
        Scanner Sc = new Scanner(System.in);
        System.out.println("Nhap tu can tra: ");
        String wordlookup = Sc.nextLine();
        boolean Check=false;
        for (int i=0;i<A.Words.size();i++) {
            if (A.Words.get(i).getWord_target().compareTo(wordlookup)==0) {
                System.out.println("Nghia cua tu can tra: " + A.Words.get(i).getWord_explain());
                Check=true;
                break;
            }
        }
        if (Check==false)
            System.out.println("Khong tim thay tu!");
    }
           

    //Ham them tu
    public void add(Dictionary A) {
        Scanner Sc = new Scanner(System.in);
        System.out.println("Nhap so tu can them: ");
        int temp = Sc.nextInt();
        for (int i=0;i<temp;i++) {
            System.out.println("Nhap tu can them: ");
            String newTarge = Sc.nextLine();
            System.out.println("Nhap nghia cua tu: ");
            String newExplain = Sc.nextLine();
            Word newWord = new Word(newTarge, newExplain);
            A.Words.add(newWord);
            System.out.println("Da xong!");
        }
    }

    //Ham sua tu
    public void edit(Dictionary A) {
        Scanner Sc = new Scanner(System.in);
        System.out.println("Nhap tu can sua: ");
        String tempWord = Sc.nextLine();
        int n = A.Words.size(),j=0;
        for (int i=0;i<n;i++) {
            if (A.Words.get(i).getWord_target().compareTo(tempWord)==0) {
                A.Words.remove(A.Words.get(i));
                j=i;
                break;
            }
        }
        System.out.println("Nhap tu can sua: ");
        String newTarge = Sc.nextLine();
        System.out.println("Nhap nghia cua tu: ");
        String newExplain = Sc.nextLine();
        Word newWord = new Word(newTarge, newExplain);
        A.Words.add(j, newWord);
        System.out.println("Da xong!");
    }

    //Ham xoa tu
    public void delete(Dictionary A) {
        Scanner Sc = new Scanner(System.in);
        System.out.println("Nhap tu can xoa: ");
        String tempWord = Sc.nextLine();
        int n = A.Words.size();
        for (int i=0;i<n;i++) {
            if (A.Words.get(i).getWord_target().compareTo(tempWord)==0) {
                A.Words.remove(A.Words.get(i));
                break;
            }
        }
        System.out.println("Da xong!");
    }

    //Ham ghi du lieu tu dien ra file
    public void dictionaryExportToFile(Dictionary A) {
        Scanner Sc = new Scanner(System.in);
        int n = A.Words.size();
        System.out.println("Nhap ten file can ghi: ");
        String Text = Sc.nextLine();
        try (FileWriter Fw = new FileWriter(new File(Text))) {
            for (int i=0;i<n;i++) {
                Fw.write(A.Words.get(i).getWord_target() + "\t" + A.Words.get(i).getWord_explain() + "\n");
            }
            Fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}

class DictionaryCommandLine extends DictionaryManagement {

    public void showAllWords(Dictionary A) {
        int n = A.Words.size();
        System.out.println("No          |English            |Vietnamese");
        for (int i=0;i<n;i++) {
            System.out.println(i+1  + "            |" + A.Words.get(i).getWord_target() + "            |" + A.Words.get(i).getWord_explain());
        }
    }

    public void dictionaryBasic(Dictionary A) {
        this.insertFromCommandline();
        this.showAllWords(A);
    }

    public void dictionaryAdvanced(Dictionary A) {
        this.insertFromFile(A);
        this.showAllWords(A);
        this.dictionaryLookup(A);
    }

    public void dictionarySearcher(Dictionary A) {
            int n=A.Words.size();
            Scanner Sc = new Scanner(System.in);
            System.out.println("Nhap tu khoa can tra: ");
            String keyWord = Sc.nextLine();
            for (int i=0;i<n;i++) {
                while (A.Words.get(i).getWord_target().startsWith(keyWord)) {
                    System.out.println(A.Words.get(i).getWord_target() + "          " + A.Words.get(i).getWord_explain());
                    break;
                }
            }
    }

}

public class BTL1 {
  
    public static void main(String[] args) {
        DictionaryCommandLine Dict = new DictionaryCommandLine();
        Dictionary A = new Dictionary();
        Dict.insertFromFile(A);       
        System.out.println("TU DIEN ANH - VIET");
        System.out.println("1. Chuc nang tra tu chinh xac");
        System.out.println("2. Chuc nang tra tu goi y");
        System.out.println("3. Chuc nang them tu");
        System.out.println("4. Chuc nang sua tu");
        System.out.println("5. Chuc nang xoa tu");
        System.out.println("6. Chuc nang hien thi du lieu tu dien");
        System.out.println("7. Chuc nang ghi du lieu tu dien ra file");
        System.out.println("8. Thoat");
        Scanner Sc = new Scanner(System.in);
        int Nhap;
        do{
        System.out.println("Nhap chuc nang ban muon thuc hien: ");
        Nhap = Sc.nextInt();
            switch (Nhap) 
            {
                case 1:{
                    Dict.dictionaryLookup(A);
                    break;
                }
                case 2:{
                    Dict.dictionarySearcher(A);
                    break;
                }
                case 3:{
                    Dict.add(A);
                    break;
                }
                case 4:{
                    Dict.edit(A);
                    break;
                }
                case 5:{
                    Dict.delete(A);
                    break;
                }
                case 6:{
                    Dict.showAllWords(A);
                    break;
                }
                case 7:{
                    Dict.dictionaryExportToFile(A);
                    break;
                }
                case 8:{
                    break;
                }
                default:{
                    System.out.println("Nhap so tu 1 den 8.");
                    break;
                }
            }
        } while (Nhap != 9);
    }
}
