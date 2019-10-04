package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class HomeController {

    @Autowired
    JobRepository jobRepository;

    @RequestMapping("/")
    public String jobList(Model model){
        model.addAttribute("jobs", jobRepository.findAll());
        return "joblist";
    }

    @RequestMapping("/add")
    public String addJob(Model model){
        model.addAttribute("job", new Job());
        return "jobform";
    }

    @PostMapping("/processjob")
    public String processForm(@ModelAttribute Job job, @RequestParam(name = "date") String date){
        String pattern = "yyyy-MM-dd'T'hh:mm";
        try{
            String formattedDate = date.substring(1,date.length()-1);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            Date realDate = simpleDateFormat.parse(formattedDate);
            job.setDate(realDate);
        }
        catch(java.text.ParseException e){
            e.printStackTrace();
        }
        jobRepository.save(job);
        return "redirect:/";
    }

    @PostMapping("/processsearch")
    public String searchResult(Model model, @RequestParam(name = "search") String search,
                               @RequestParam(name = "category") String category){

        if (category.equals("1")){
            model.addAttribute("jobs", jobRepository.findByTitleContainingIgnoreCase(search));
        }
        else if(category.equals("2")){
            model.addAttribute("jobs", jobRepository.findByAuthorContainingIgnoreCase(search));
        }
        else if (category.equals("3")){
            model.addAttribute("jobs", jobRepository.findByDescriptionContainingIgnoreCase(search));
        }
        else if (category.equals("4")){
            model.addAttribute("jobs", jobRepository.findByPhoneNum(search));
        }
        return "searchlist";
        }

    @RequestMapping("/detail/{id}")
    public String showJob(@PathVariable("id") long id, Model model) {
        model.addAttribute("job", jobRepository.findById(id).get());
        return "showdetail";
    }

    @RequestMapping("/update/{id}")
    public String updateJob(@PathVariable("id") long id, Model model) {
        model.addAttribute("job", jobRepository.findById(id).get());
        return "jobform";
    }

    @RequestMapping("/delete/{id}")
    public String deleteJob(@PathVariable("id")long id) {
        jobRepository.deleteById(id);
        return "redirect:/";
    }
}
