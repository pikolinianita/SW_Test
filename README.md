# SW_Test

[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=pikolinianita_SW_Test&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=pikolinianita_SW_Test)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=pikolinianita_SW_Test&metric=coverage)](https://sonarcloud.io/dashboard?id=pikolinianita_SW_Test)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=pikolinianita_SW_Test&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=pikolinianita_SW_Test)

Program łączy się z <https://Swapi.dev> , zapisuje dane w bazie danych (H2, in memory), zapytany zwraca dane z bazy danych. Dokładna specyfikacja w pliku pdf, ale:
- łączy się z swapi.dev, bo swapi.co nie działa

Wszystkie testy są wykonywane jako "unit tests", mimo że większość zalicza się do integracyjnych. Ale ponieważ całość testów wykonuje się krótko (w minutę), większość programu to interakcje z bazą H2 lub SWapi, to częste wykonywanie wszyskich testów ma sens.

