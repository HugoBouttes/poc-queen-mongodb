# Scénario de test

## Scénario pour une unité-enquêtée donnée (idSurveyUnit):

- **initialisation**

  - **GET** sur le modèle de questionnaire (_/api/campaign/${idCampaign}/questionnaire_)
  - **GET** sur les métadonnées de questionnaires (_/api/campaign/${idCampaign}/metadata_)
  - **GET** pour récupérer la liste des nomenclautes (_/api/campaign/${idCampaign}/required-nomenclatures_)
    - puis un **GET** sur chaque élément de la liste reçue sur (_/api/nomenclature/{id}_)

- **remplissage du questionnaire pour l'unité enquêtée d'identifiant idSurveyUnit**
  - itérer de 0 à 91
  - pour chaque itération faire:
    - **PUT** du contenu de fichier data-{iteration}.json sur _/api/survey-unit/{idSurveyUnit}/data_
    - **POST** du contenu de fichier paradata-{iteration}.json sur _/api/paradata_
    - petite pause entre 3 et 10 sec