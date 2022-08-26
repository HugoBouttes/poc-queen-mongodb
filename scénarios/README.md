# Scénario de test

## Scénario pour une unité-enquêtée donnée (idSurveyUnit):

- **initialisation**

  - **GET** sur le modèle de questionnaire (_/api/campaign/${idCampaign}/questionnaire_)
  - **GET** sur les métadonnées de questionnaires (_/api/campaign/${idCampaign}/metadata_)
  - **GET** pour récupérer la liste des nomenclautes (_/api/campaign/${idCampaign}/required-nomenclatures_)
    - puis un **GET** sur chaque élément de la liste reçue sur (_/api/nomenclature/{id}_)

- **remplissage du questionnaire pour l'unité enquêtée d'identifiant idSurveyUnit**
  - itérer de 0 à 92
  - pour chaque itération faire:
    - **PUT** aléatoire d'un extrait de [data](https://minio.lab.sspcloud.fr/hbouttes/Data.json)  sur _/api/survey-unit/{idSurveyUnit}/data
    - **PUT** aléatoire d'un extrait de [state-data](https://minio.lab.sspcloud.fr/hbouttes/StateData.json)  sur _/api/survey-unit/{idSurveyUnit}/data
    - **POST** daléatoire d'un extrait de [paradata](https://minio.lab.sspcloud.fr/hbouttes/Paradata.json) sur _/api/paradata_
    - petite pause 
