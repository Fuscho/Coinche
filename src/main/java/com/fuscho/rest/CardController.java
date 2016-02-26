package com.fuscho.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/recipes")
@Slf4j
public class CardController {


    /**
     * REST controller for recipe
     * @param recipeService : Service for manage recipe
     * @param pictureService : Service for manage picture
     */
    @Autowired
    public CardController(RecipeService recipeService, PictureService pictureService){
        this.recipeService = recipeService;
        this.pictureService = pictureService;
    }


    /**
     * Search recipe from db
     * @param filtres : Crtieria to find only requested recipes
     * @return a list of recipe
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.GET, value = "")
    public List<Recipe> searchRecipes(@RequestParam Map<String, String> filtres) throws IOException {
        RecipeFilter recipeFilter;
        if(filtres.size() > 0){
            HashMap tags = new ObjectMapper().readValue(filtres.get("tags"), HashMap.class);
            recipeFilter = RecipeFilter.builder()
                    .name(filtres.get("name"))
                    .tags(tags)
                    .build();
        } else {
            recipeFilter = new RecipeFilter();
        }

        return recipeService.search(recipeFilter);
    }

    /**
     * Save a recipe in DB
     * @param recipe : The recipe to save
     * @return saved recip
     */
    @RequestMapping(method = RequestMethod.POST, value = "")
    public Recipe saveRecipes(@RequestBody Recipe recipe){
        return recipeService.save(recipe);
    }

    /**
     * Delete a recipe
     * @param recipeToDelete : ID of the recipe to delete
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{recipeToDelete}")
    public HttpStatus deleteRecipes(@PathVariable String recipeToDelete) {
        recipeService.delete(recipeToDelete);
        return HttpStatus.OK;
    }

    /**
     * Get all tags attached to recipes
     * @return a list of tags
     */
    @RequestMapping(method = RequestMethod.GET, value = "/tags")
    public List<Tags> getAllTagsRecipes(){
        return recipeService.getAllTags();
    }

    /**
     * Get a random recipe
     * @return a recipe
     */
    @RequestMapping(method = RequestMethod.GET, value = "/random")
    public Recipe getRandomRecipe(){
        return recipeService.getRandomRecipe();
    }

    /**
     * Save a picture on disk
     * @param file : The picture to save
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, value = "/picture")
    public void uploadRecipePicture(@RequestBody MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            pictureService.uploadPicture(file);
        } else {
            throw new PictureSaveNoContentException("You failed to upload because the file was empty.");
        }
    }
}
