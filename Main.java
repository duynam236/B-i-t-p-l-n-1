package com.company;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

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
	private ArrayList<Word> list = new ArrayList();
    public void setList(String word_target,String word_explain)
    {
        Word new_word = new Word(word_target,word_explain);
        this.list.add(new_word);
    }
    public ArrayList<Word> getList(){
        return list;
}

class DictionaryManagement {
    Dictionary A = new Dictionary();

    // Phien ban tu dien so khai
    public void insertFromCommandline() {
        Scanner One = new Scanner(System.in);
        System.out.println("Nhap so tu: ");
        int n = One.nextInt();
        for (int i=1;i<=n;i++) {
            System.out.println("Nhap tu tieng anh: ");
            String Eword = One.nextLine();
            System.out.println("Nhap tu tieng viet: ");
            String Vword = One.nextLine();
            Word newWord = new Word(Eword, Vword);
            A.Words.add(newWord);
        }
    }

    public void insertFromFile() {
        Dictionary B = new Dictionary();
        FileInputStream fis = new FileInputStream("C:\Dictionary1.txt");
        try (Scanner Sc = new Scanner(fis)) {
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

    public void dictionaryLookup() {

    }
}

class DictionaryCommandLine extends DictionaryManagement {

    public void showAllWords() {
        int n = A.Words.size();
        System.out.println("No          |English            |Vietnamese" + "\n");
        for (int i=1;i<=n;i++) {
            System.out.println(i + "            |" + A.Words.get(i).getWord_target() + "            |" + A.Words.get(i).getWord_explain() +"\n");
        }
    }

    public void dictionaryBasic() {
        this.insertFromCommandline();
        this.showAllWords();
    }


}

public class Main {

    public static void main(String[] args) {
	// write your code here
    }
}
