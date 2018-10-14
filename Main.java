package com.company;

import java.util.ArrayList;
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

class Dictionary 
{
    ArrayList <Word> Words = new ArrayList();
	
}

class DictionaryManagement {
    Dictionary A = new Dictionary();

    // Phien ban tu dien so khai
	//Ham nhap du lieu tu dien bang ban phim
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

	//Ham nhạp du lieu tu dien tu tep
    public void insertFromFile() {
        Dictionary B = new Dictionary();
        try (Scanner Sc = new Scanner(new File("C:/Dictionary1.txt"))) {
            String Str0,Str1;
            while (Sc.hasNext()) {
                Str0 = Sc.next();
                Str1 = Sc.nextLine();
                Word newWord = new Word(Str0, Str1);
                B.Words.add(newWord);
            }
        }
        catch (Exception e) 
	{
		System.out.println(e.getClass().getSimpleName() + " " + e.getMessage());
        }
    }

    public void dictionaryLookup() {

    }
}

class DictionaryCommandLine extends DictionaryManagement {
	DictionaryManagement dictionarymanagement = new DictionaryManagement();

    //Lay du lieu tu text
    public void setTxtDictionaryManagement(){
        dictionarymanagement.insertFromFile();
    }
	
	//Ham hien thi du lieu tu dien ra man hinh
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
    public void dictionaryAdvanced() 
	{
    	Dictionary dictionary = new Dictionary();
        DictionaryManagement input = new DictionaryManagement();     
        input.insertFromFile();
        input.dictionaryLookup();
        
    }


}

public class Main {

    public static void main(String[] args) {
	// write your code here
    }
}
