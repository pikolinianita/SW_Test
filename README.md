# SW_Test
[![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=pikolinianita_SW_Test)](https://sonarcloud.io/dashboard?id=pikolinianita_SW_Test)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=pikolinianita_SW_Test&metric=coverage)](https://sonarcloud.io/dashboard?id=pikolinianita_SW_Test)

W przesłanej wersji nie było implementacji **Equals() i HashCode()** w klasach Report, Movie, Hero. Program działał poprawnie - jest zbyt prosty żeby ich brak się ujawnił. **Przepraszam**. Program przełany do Państwa jest utworzony z wersji e39c6a78c4a6cf1d89b3726126ddfade84291ddd

Program łączy się z https://Swapi.dev , zapisuje dane w bazie danych (H2, in memory), zapytany zwraca dane z bazy danych. Dokładna specyfikacja w pliku pdf, ale:
 - łączy się z swapi.dev, bo swapi.co nie działa
 - w bazie jest tylko 6 filmow (epizody 1-6), nie ma trzech ostatnich

Wszystkie testy są wykonywane jako "unit tests", mimo że większość zalicza się do integracyjnych. Ale ponieważ całość testów wykonuje się krótko (w minutę), większość programu to interakcje z bazą H2 lub SWapi, to częste wykonywanie wszyskich testów ma sens.

