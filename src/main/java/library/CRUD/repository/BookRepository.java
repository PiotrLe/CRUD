package library.CRUD.repository;

/**
 *
 */

import library.CRUD.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author A4260900
 *
 */

//This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
//CRUD refers Create, Read, Update, Delete
@Repository
public interface BookRepository extends CrudRepository<Book, String>, QuerydslPredicateExecutor<Book> {

//    @Query("from CartItem ci join ShoppingCart sc on sc.id=ci.parentShoppingCart where sc.id=:cartId and ci.id=:cartItemId")
//    CartItem findCartItemByCartAndItemId(@Param("cartId") String cartId, @Param("cartItemId") String cartItemId);
//
//    @Query("SELECT sc.ownerId FROM ShoppingCart sc WHERE sc.id=:shoppingCartId")
//    String getOwnerIdForCartId(@Param("shoppingCartId") String shoppingCartId);
}