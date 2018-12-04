package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("searchType", "all");
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "results")
    public String search(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        /*
                return all if all is SearchType and no searchterm was entered
                 */
        if (searchTerm.isEmpty() && searchType.contentEquals("all")) {
            ArrayList<HashMap<String, String>> jobs = JobData.findAll();
            model.addAttribute("columns", ListController.columnChoices);
            model.addAttribute("title", "All jobs");
            model.addAttribute("searchType", searchType);
            model.addAttribute("jobs", jobs);
            return "search";
                /*
                This should search all rows and return anything with matching search term
                 */
        } else if (searchType.contentEquals("all")) {
            ArrayList<HashMap<String, String>> jobs = JobData.findByValue(searchTerm);
            model.addAttribute("columns", ListController.columnChoices);
            model.addAttribute("title", "All jobs with " + searchTerm);
            model.addAttribute("searchType", searchType);
            model.addAttribute("jobs", jobs);
            return "search";
                /*
                return  all rows that match searchterm and searchtype
                 */
        } else {
            ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("columns", ListController.columnChoices);
            model.addAttribute("title", "Showing "+searchType+" with "+searchTerm);
            model.addAttribute("searchType", searchType);
            model.addAttribute("jobs", jobs);
            return "search";
        }
    }
}
