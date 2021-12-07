package Classes;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Creates a SearchByKeyword class, which implements Filter
 */
public class SearchByKeyword implements Filter {

    /**
     * sortList method which sorts the list passed through the parameter
     * @return ArrayList<JobListing>, a sorted list
     */
    @Override
    public ArrayList<JobListing> sortList(ArrayList<JobListing> jobList) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please input a word to search for: ");
        String input = scanner.nextLine(); 
        ArrayList<JobListing> outputList = new ArrayList<JobListing>(); 
        boolean wasAdded = false;
        for(int i = 0; i < jobList.size(); i++) {
            JobListing job = null; 
            if(findSubString(jobList.get(i).getJobTitle(), input) || findSubString(jobList.get(i).getJobDescription(), input)) {
                job = jobList.get(i);
                wasAdded = true; 
            }
            for(int k = 0; k < jobList.get(i).getSkillList().size(); k++) {
                if(findSubString(jobList.get(i).getSkillList().get(k), input)) {
                job = jobList.get(i);
                wasAdded = true;
                }
            }
            for(int k = 0; k < jobList.get(i).getExperienceList().size(); k++) {
                if(findSubString(jobList.get(i).getExperienceList().get(k), input)) {
                    job = jobList.get(i);
                    wasAdded = true; 
                }
            }
            if(job != null) {
                outputList.add(job);
            }
        }

        if(!wasAdded) {
            System.out.println("That keyword could not be found, please back out and try again.");
            return null;
        }
        return outputList;
    }  
    
    /**
     * findSubString method which searches for a specific word
     * @param sentence String, Sentence being looked through
     * @param word String, Specific word being searched for
     * @return boolean, True if word is found, false if not
     */
    private static boolean findSubString(String sentence, String word) {
        for(int i = 0; i < sentence.length(); i++) {
            if(i + word.length() > sentence.length() || word.length() == 0)
                return false; 
            if(sentence.substring(i, i + word.length()).equalsIgnoreCase(word))
                return true; 
        }
        return false; 
    }   
}


