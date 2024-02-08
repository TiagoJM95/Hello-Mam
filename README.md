<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a name="readme-top"></a>
<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Don't forget to give the project a star!
*** Thanks again! Now go create something AMAZING! :D
-->



<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![Contributors](https://img.shields.io/github/contributors/TiagoJM95/Hello-Mam.svg?style=for-the-badge)](https://github.com/TiagoJM95/Hello-Mam/graphs/contributors)

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/TiagoJM95/Hello-Mam">
    <img src="images/logo.png" alt="Logo" width="240" height="240">
  </a>

<h3 align="center">Hello Mam</h3>

  <p align="center">
    Hello-Mam is a multi modal multimedia recommendation engine.
    <br />
</div> 
<br />



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

[![Product Name Screen Shot][product-screenshot]](https://example.com)

Hello-Mam is a multi-modal multimedia recommendation engine. Once they access it, users can get recommendations based on the highest rated medias on the selected external API's. It stores the User entities, Media entities and Rating entities.
Our Spring Boot APP (Hello-Mam) communicates with our Hello-Grandma Intermediate API and uses Redis as cache for super speedy requests.<br />
Our Hello-Grandma Intermediate API has the job of connecting to any external API, translating the information needed from the client requests, transforming the information it receives, storing it in MongoDB and returning it to our Spring Boot App. It stores the entities relative to each of the media that our app allows. It uses Quarkus Cache for speedy requests.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



### Built With

* [![Quarkus](https://img.shields.io/badge/Quarkus-4691A3?style=for-the-badge&logo=quarkus&logoColor=white)](https://quarkus.io/)
* [![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
* [![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
* [![MongoDB](https://img.shields.io/badge/MongoDB-47A248?style=for-the-badge&logo=mongodb&logoColor=white)](https://www.mongodb.com/)
* [![Redis](https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white)](https://redis.io/)
<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

### Prerequisites

Download and install the following tools:
* Docker and Docker Compose:
  ```sh
  https://docs.docker.com/engine/install/
  https://docs.docker.com/compose/install/
  ```

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/TiagoJM95/Hello-Mam.git
   ```
3. Access the terminal and navigate to the root folder of the project
   ```sh
   docker-compose up
   ```
4. Open your browser and access the following URL
   ```sh
    http://localhost:8082/helloMam/swagger-ui
    ```

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- LICENSE -->
## License

Distributed under the Mindswap License.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTACT -->
## Contact

José Ribeiro - jruysaribeiro@gmail.com </br>
Tiago Moreira - tiagojm95@gmail.com </br>
Carina Cunha - car930193@gmail.com

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

[![TMDB](https://img.shields.io/badge/TMDB-01D277?style=for-the-badge&logo=themoviedatabase&logoColor=white)](https://www.themoviedb.org/)
[![IGDB](https://img.shields.io/badge/IGDB-3498DB?style=for-the-badge&logo=thegamesdb&logoColor=white)](https://www.igdb.com/)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[product-screenshot]: images/screenshot.png