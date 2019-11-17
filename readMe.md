# Achillenium
Фреймворк основан на Akita (BDD), Apache Cayenne (ORM), Selenoid, Allure Report
## Предварительные настройки:

**1. Проверить,что установлена Java 8**
**2. Необходимо установить следующие плагины для Intellij IDEA:**
* Cucumber for Java
* Gherkin
* Lombok

**3. Настроить плагин Lombok:**
* В разделе *Build, Execution, Deployment -> Compiler -> Annotation Processors* требуется поставить галочку *Enable annotation processing*
* В разделе *Other settings -> Lombok plugin* требуется поставить галочку *Enable lombok plugin for this project*

**4. Проверить, что установлены следующие env переменные (переменные окружения):**

**5. Проверить, что установлен gradle на локальную машину с которой будет запускаться проект**
* При запуске проекта указать: Enable auto import и указать путь до gradle home

**6. Скачать Apache Cayenne (https://cayenne.apache.org/download/) с официального сайта и просмотреть обучающий ролик (https://www.youtube.com/watch?v=0H5HIA03xFc)**



### Общие шаги
 Все базовые шаги находятся в библиотеке в классах DefaultSteps, DefaultApiSteps. Вы можете пользоваться этими шагами при составлении новых сценариев.
 
### Пользовательские шаги
 Если есть необходимость реализовать свои специфичные шаги, это можно сделать, создав новый класс в пакете src/main/java/steps(или любая другая папка указанная в generateRunner.glue). 

### Как воспользоваться возможностями akitaScenario?
Для доступа к общим методам сценариев, потребуется в каждом классе шагов прописать вот такую строку.

> Akita akitaScenario = Akita.getInstance();

Для работа с новыми стораницами необходимо в классе страницы унаследоваться от AkitaPage:

> extends AkitaPage

### Время загрузки страницы
Реализована возможность управления временем ожидания появления элемента на странице.
Чтобы установить timeout, отличный от базового, нужно добавить в **application.properties**
строку
> waitingAppearTimeout=150000

### Отображение в отчете справочной информации
Для того, чтобы в отчете появился блок  Output с информацией, полезной для анализа отчета, можно воспользоваться следующим методом(это позволит добавить данные в отчёт, который генерится посредством gCR)

> akitaScenario.write("Текущий URL = " + currentUrl + " \nОжидаемый URL = " + expectedUrl);

#### Запуск тестов
Скачанный проект с тестами нужно открыть как gradle-проект, далее ты можешь запускать тесты одним
из удобных тебе способов
* gradle задачи:  **Clean**, **Test**, **GenerateCucumberReport**
* контекстное меню для конкретного feature-файла, где следует не забывать про установку VM Properties.
  -Dbrowser=chrome
* из терминала
 ```
   > gradlew clean test -i
   > gradlew clean generateCucumberReport --debug
   > gradlew clean test -Dbrowser=chrome -Ptag=@test
  ```

  Для проектов предусмотрен параллельный запуск всех feature-файлов (для каждого feature-файла создается свой runner), запуск тестов на удаленной машине (-PremoteHub), смена бразера (-Pbrowser=chrome), запуск тестов согласно выставленным на сценариях тегам (-Ptag).
   
### Отчет о тестировании
   Akita позволяет генерировать красивые отчеты о прошедщем тестировании.
   Генерация отчета осуществляется запуском команды
   ```
   gradlew generateCucumberReport        или gradlew gCR
   ```

Отчет создается в папке  **build/reports**.  Основным является файл **"overview-features.html"**.



