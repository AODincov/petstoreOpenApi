/*
 * OpenAPI Petstore
 * This is a sample server Petstore server. For this sample, you can use the api key `special-key` to test the authorization filters.
 *
 * The version of the OpenAPI document: 1.0.0
 *
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.api;

import org.apache.commons.lang3.RandomStringUtils;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;

import java.io.File;

import org.openapitools.client.auth.ApiKeyAuth;
import org.openapitools.client.auth.Authentication;
import org.openapitools.client.auth.OAuth;
import org.openapitools.client.model.Category;
import org.openapitools.client.model.ModelApiResponse;
import org.openapitools.client.model.Pet;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openapitools.client.model.Tag;
import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * API tests for PetApi
 */
@Disabled
public class PetApiTest {

    private final PetApi api = new PetApi();

    /**
     * Add a new pet to the store
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void addPetTest() throws ApiException {

        //как я понял авторизация выполняется как то так.
        //  не могу проверить т.к. в petstore нет проверки авторизации
        PetApi authorizedApi = new PetApi(new ApiClient(new HashMap<String, Authentication>(){{
            put("api_key", new ApiKeyAuth("header", "api_key").setApiKey("special-key"));
            put("petstore_auth", new OAuth("special-key"));
        }}));

        Integer id = 123;
        Pet pet = new Pet();
        pet
                .id(id)
                .category(new Category()
                        .id(0)
                        .name("string"))
                .name(RandomStringUtils.random(14, true, false))
                .photoUrls(Collections.singletonList("qeqrq"))
                .tags(Collections.singletonList(new Tag()
                        .id(12)
                        .name("fwaf")))
                .status(Pet.StatusEnum.AVAILABLE)

        ;
        Pet response = api.addPet(pet);
        assertThat(pet.toJson(), equalTo(response.toJson()));
        assertThat(pet.toJson(), equalTo(api.getPetById(id).toJson()));
    }

    /**
     * Deletes a pet
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void deletePetTest() throws ApiException {

        Integer id = 123;
        Pet pet = new Pet();
        pet
                .id(id)
                .category(new Category()
                        .id(0)
                        .name("string"))
                .name(RandomStringUtils.random(14, true, false))
                .photoUrls(Collections.singletonList("qeqrq"))
                .tags(Collections.singletonList(new Tag()
                        .id(12)
                        .name("fwaf")))
                .status(Pet.StatusEnum.AVAILABLE)

        ;
        api.addPet(pet);


        Long petId = id.longValue();
        //String apiKey = "special-key";
        //petstore не проверяет ключ авторизации и его наличие
        String apiKey = null;
        api.deletePet(petId, apiKey);

        // если код ответа не в диапазоне 200 - 299 пробрасывает исключение
        // данное поведение зашито в okhttp3
        // у ретрофита такого кстати нет
        // такое поведение вынуждает оборачивать проверки негативных кодов ответа

         Pet resp = null;

        try {
            resp = api.getPetById(petId.intValue());
        } catch (ApiException e){
            assertThat(e.getCode(),equalTo(404));
        } finally {
            if (resp != null){
                throw new RuntimeException();
            }
        }

    }

    /**
     * Finds Pets by status
     * <p>
     * Multiple status values can be provided with comma separated strings
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void findPetsByStatusTest() throws ApiException {
        List<String> status = null;
        List<Pet> response = api.findPetsByStatus(status);
        // TODO: test validations
    }

    /**
     * Finds Pets by tags
     * <p>
     * Multiple tags can be provided with comma separated strings. Use tag1, tag2, tag3 for testing.
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void findPetsByTagsTest() throws ApiException {
        List<String> tags = null;
        List<Pet> response = api.findPetsByTags(tags);
        // TODO: test validations
    }

    /**
     * Find pet by ID
     * <p>
     * Returns a single pet
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getPetByIdTest() throws ApiException {
        Integer petId = null;
        Pet response = api.getPetById(petId);
        // TODO: test validations
    }

    /**
     * Update an existing pet
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void updatePetTest() throws ApiException {
        Pet pet = null;
        Pet response = api.updatePet(pet);
        // TODO: test validations
    }

    /**
     * Updates a pet in the store with form data
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void updatePetWithFormTest() throws ApiException {
        Long petId = null;
        String name = null;
        String status = null;
        api.updatePetWithForm(petId, name, status);
        // TODO: test validations
    }

    /**
     * uploads an image
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void uploadFileTest() throws ApiException {
        Long petId = null;
        String additionalMetadata = null;
        File _file = null;
        ModelApiResponse response = api.uploadFile(petId, additionalMetadata, _file);
        // TODO: test validations
    }

}
