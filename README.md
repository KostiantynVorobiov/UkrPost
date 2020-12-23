 #REST

#### Технології: 
Spring Boot, JpaRepository, H2, REST

#### Опис проекту: 
Проект імітує роботу інтернет магазину, з можливістю створювати юзера, закидати йому гроші на рахунок, додавати новий продукт до БД та виконувати основні REST методи, при додаванні нового продукту, якщо ми не вказали продукту скидку то в нього по дефолту встановлюється скидка в 0%, але ми в любий час можемо задати своє значення. Для здійснення покупки спочатку користувач додає продукти до свого кошика, а потім вже здійснює оплату своїх товарів.

Щоб протестувати роботу рекомендую використовувати `Postman`.

__POST__: `localhost:8080/users/create_user` - створить нового користувача.

Body -> raw -> Text (JSON):
```json
{
    "email" : "ivanov@i.ua",
    "firstName" : "Ivan",
    "lastName" : "Ivanov",
    "moneyAccount" : "100"
}
```

__POST__: `localhost:8080/products` - створить новий продукт.
```json
{
    "name" : "banana",
    "category" : "food",
    "price" : 25.0
}
```
або 
```json
{
    "name" : "bread",
    "category" : "food",
    "price" : 15.0,
    "discount" : 10
}
```

__PUT__: `localhost:8080/users/add_money/<id>/<summa>` - добавить гроші `summa` на аккаунт користувача `id`.

__GET__: `localhost:8080/products` - поверне всі продукти з БД, але якщо `http://localhost:8080/products?category=<category>` - то поверне продукти по вказаній категорії.

__PUT__: `localhost:8080/products/<prodId>/<discount>` - вибраному продукту встановить скидку, обо змінить її.

__POST__: `localhost:8080/carts/addProduct?userId=<userId>&productId=<prodId>` - додасть продукт в кошик користувача.

__GET__: `localhost:8080/carts/pay/<cartId>` - виконає перевірку чи достатньо у користавача коштів, якщо так то виконає покупку і зніме з рахунку необхідну суму грошей, якщо ні то видать повідомлення що коштів недостатньо.



#### Автор.
[Kostiantyn Vorobiov](https://github.com/KostiantynVorobiov)
 