import baseTest.BaseTest;
import org.junit.Before;
import org.junit.Test;

public class BasketTests extends BaseTest {

    @Before
    public void navigateToProductOnOutletPage() {
        initPage
                .getHomePage()
                .openHomePage()
                .getHeaderElement().clickOnMenuButton()
                .getMenuElement().clickOnOutletMenuButton()
                .checkRedirectingToOutletPage()
                .getPropertiesFirstProduct()
                .clickOnFirstProduct();
    }

    /**
     * test adds the first product from 'Outlet' page to basket,
     * then verifies correct update of product quantity and total price
     * in 'Basket' pop-up on product page,
     * and then on final 'Basket' page
     */
    @Test
    public void testChangingProductQuantityInBasket() {
        int numberOfProducts = 1;
        int numberOfProductsChanged = 2;
        initPage
                .getProductPage()
                .openProductPage()
                .checkProductNameAndPriceAreCorrect()
                .addProductToBasket(numberOfProducts)
                .checkProductNameAndPriceInCheckoutPopup()
                .closeCheckoutPopup()
                .getHeaderElement()
                .checkProductAddedToBasket()
                .clickOnBasketButton()
                .checkProductAmountAndPriceInBasket() //тут тільки кількість та ціна, бо ім'я не збігатися з іменем на сторінці продукту
                .changeProductNumberInBasket(numberOfProductsChanged)
                .checkProductAmountAndPriceInBasket()
                .openBasketPage()
                .changeProductNumberInBasket(numberOfProducts)
                .checkProductAmountAndPriceInBasket();
    }

}
