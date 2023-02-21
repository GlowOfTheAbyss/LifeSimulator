
# LifeSimulator

### Для работы проекта вам понадобятся следующие библиотеки:

- xmlbeans-2.6.0.jar
- poi-3.12-20150511.jar
- dsdpoi-examples-3.12-20150511.jar
- poi-excelant-3.12-20150511.jar
- poi-ooxml-3.12-20150511.jar
- poi-ooxml-schemas-3.12-20150511.jar
- poi-scratchpad-3.12-20150511.jar

которые могут быть скачены по этой [ссылке](https://www.apache.org/dyn/closer.cgi/poi/release/bin/poi-bin-3.12-20150511.zip)

А так же эти библиотеки:

- [jackson-annotations-2.13.2.jar](https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-annotations/2.13.2/jackson-annotations-2.13.2.jar)
- [jackson-core-2.13.2.jar](https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-core/2.13.2/jackson-core-2.13.2.jar)
- [jackson-databind-2.13.2.2.jar](https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-databind/2.13.2/jackson-databind-2.13.2.jar)
- [jackson-dataformat-xml-2.13.2.jar](https://repo1.maven.org/maven2/com/fasterxml/jackson/dataformat/jackson-dataformat-xml/2.13.2/jackson-dataformat-xml-2.13.2.jar)
- [jackson-dataformat-yaml-2.13.2.jar](https://repo1.maven.org/maven2/com/fasterxml/jackson/dataformat/jackson-dataformat-yaml/2.13.2/jackson-dataformat-yaml-2.13.2.jar)
- [snakeyaml-1.30.jar](https://repo1.maven.org/maven2/org/yaml/snakeyaml/1.30/snakeyaml-1.30.jar)
- [stax2-api-4.2.1.jar](https://repo1.maven.org/maven2/org/codehaus/woodstox/stax2-api/4.2.1/stax2-api-4.2.1.jar)
- [woodstox-core-6.2.8.jar](https://repo1.maven.org/maven2/com/fasterxml/woodstox/woodstox-core/6.2.8/woodstox-core-6.2.8.jar)

### Краткое описание проекта

- Сделал максимально на сколько смог простое добавление новых животных в программу.
- Немного усложнил логику поведения для животных.
- Проект запускается через Main класс.
- Получилось реализовать почти все требования, животные передвигаются, едят и размножаются, статистика выводиться, реализована иерархия классов.
- Не получилось реализовать многопоточность, как бы я не пытался все сводиться к деад локам которые я не могу никак исправить.
- Утка может съесть гусеницу, и в принципе любое другое животное ели вам это понадобиться.
- Конфиги находятся в src/main/resources/
    - foodTable.xlsx - таблица с шансами на съедение.
    - setting.json - настройки симуляции.
- Конфигурации грузятся в main.java.fileManager.FileLoader