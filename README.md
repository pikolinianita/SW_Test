# SW_Test
[![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=pikolinianita_SW_Test)](https://sonarcloud.io/dashboard?id=pikolinianita_SW_Test)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=pikolinianita_SW_Test&metric=coverage)](https://sonarcloud.io/dashboard?id=pikolinianita_SW_Test)

Program łączy się z https://Swapi.dev , zapisuje dane w bazie danych (H2, in memory), zapytany zwraca dane z bazy danych. Dokładna specyfikacja w pliku pdf, ale:
 - łączy się z swapi.dev, bo swapi.co nie działa
 - w bazie jest tylko 6 filmow (epizody 1-6), nie ma trzech ostatnich

Wszystkie testy są wykonywane jako "unit tests", mimo że większość zalicza się do integracyjnych. Ale ponieważ całość testów wykonuje się krótko (w minutę), większość programu to interakcje z bazą H2 lub SWapi, to częste wykonywanie wszyskich testów ma sens.
