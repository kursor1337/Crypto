# Crypto
Trainee Test task for MobileUp


## Декомпозиция
1. Сущности - 30 минут
  * Криптовалюта (Market, с id'шником, символом, ссылкой на изображение и тд)
  * Описание криптовалюты (description, categories)
2. UseCases первого экрана - 30 минут 
  * Получение стоимостей криптовалют в выбранной валюте
3. UseCases второго экрана - 30 минут
  * Получение описания криптовалюты
4. Два репозитория, по одному на каждую сущность - 2 часа
5. Верстка экранов и создание вьюмоделей для них - 4 часа
  * Экран загрузки
  * Экран "Попробовать снова"
  * Экран списка криптовалют (со списком, чипсами и тулбаром и pull to refresh)
  * Экран описания криптовалюты (с тулбаром)
6. Навигация - 30 минут
7. Исправление ошибок - 4 часа


## Стэк
* [Kotlin](https://kotlinlang.org/)
* [Retrofit](https://square.github.io/retrofit/)
* [Koin](https://insert-koin.io/)
* [KotlinX Serialization](https://kotlinlang.org/docs/serialization.html)
* [Jetpack Compose](https://developer.android.com/jetpack/compose/)
* [Compose Navigation](https://developer.android.com/jetpack/compose/navigation)
* [Single Activity](https://www.youtube.com/watch?v=2k8x8V77CrU)
* [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html#documentation)
* [MVVM (ViewModel and LiveData)](https://developer.android.com/topic/libraries/architecture/viewmodel)
* [Clean Arch](https://developer.android.com/topic/architecture)

## Дополнительные библиотеки (использовались для реализации небольших фич)
* [Accompanist: SwipeRefresh](https://github.com/google/accompanist/tree/main/swiperefresh)
* [Coil](https://coil-kt.github.io/coil/)
* [Toasty](https://github.com/GrenderG/Toasty)
