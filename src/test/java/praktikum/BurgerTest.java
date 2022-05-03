package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest implements SetUp {
    private Burger burger;
    @Mock
    private Bun bun;
    @Mock
    private Ingredient ingredient;

    @Override
    @Before
    public void createObject() {
        burger = new Burger();
    }

    @Test
    public void setBunsWithDefaultValueBunSet() {
        burger.setBuns(bun);
        Assert.assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientWithOneIngredientListSizeEqualOne() {
        burger.addIngredient(ingredient);
        Assert.assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void removeIngredientListIsEmpty() {
        //добавили один ингредиент
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        Assert.assertTrue("List is not empty", burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredientReturnMovedValue (){
    burger.addIngredient(new Ingredient(SAUCE,"First",1));
    burger.addIngredient(new Ingredient(FILLING,"Second",2));
    burger.moveIngredient(0,1);
    Assert.assertEquals("Incorrect Type",SAUCE,burger.ingredients.get(1).type);
    Assert.assertEquals("Incorrect name","First",burger.ingredients.get(1).name);
    Assert.assertEquals("Incorrect price",1,burger.ingredients.get(1).price,0);
    }
    @Test
    public void getPriceReturnCorrectValue(){
        float price = 10;
        float expectedPrice = price * 2 + price;
        when(bun.getPrice()).thenReturn(price);
        when(ingredient.getPrice()).thenReturn(price);
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        Assert.assertEquals("Incorrect price",expectedPrice,burger.getPrice(),0);
    }
    @Test
    public void getReceipt(){
        String nameBun = "bulochka";
        String nameIngredient = "ogurchik";
        IngredientType ingredientType = FILLING;
        String expectedReceipt = String.format("(==== %s ====)" + "\n" +
                        "= %s %s =" + "\n" +
                        "(==== %s ====)" + "\n" + "\n" +
                        "Price: %f%n",
                nameBun, ingredientType.toString().toLowerCase(), nameIngredient, nameBun, ingredient.getPrice());
        burger.setBuns(bun);
        when(bun.getName()).thenReturn(nameBun);
        burger.addIngredient(ingredient);
        when(ingredient.getName()).thenReturn(nameIngredient);
        when(ingredient.getType()).thenReturn(ingredientType);
        Assert.assertEquals(expectedReceipt, burger.getReceipt());
    }


}
