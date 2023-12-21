package ru.stocktaking.stocktaking.controller.search;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.stocktaking.stocktaking.service.search.SearchService;

import java.util.List;

/**
 * Created by AndreyDo16 on 21.12.2023
 */

@Controller
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/results")
    public String searchResults(
            @RequestParam(name = "searchInventoryNumber", required = false) long searchInventoryNumber,
            Model model) {

        // Вызов сервисного метода для выполнения поиска
        Object searchResults = searchService.searchByInventoryNumber(searchInventoryNumber);

        // Передача результатов поиска в модель
        model.addAttribute("searchResults", searchResults);

        // Верните представление для отображения результатов
        return "search/search_results_inventory_number";
    }
}
