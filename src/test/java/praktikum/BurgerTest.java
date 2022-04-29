package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
    public void getPrice(){

    }
}
