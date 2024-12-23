create database if not exists cabinet;

use cabinet;
CREATE TABLE Patients (
  id_patient INT PRIMARY KEY AUTO_INCREMENT,
  nom VARCHAR(100) NOT NULL,
  prenom VARCHAR(100) NOT NULL,
  cin VARCHAR(50) UNIQUE NOT NULL,
  telephone VARCHAR(15),
  email VARCHAR(100),
  date_naissance DATE
);

CREATE TABLE Medecins (
  id_medecin INT PRIMARY KEY AUTO_INCREMENT,
  nom VARCHAR(100) NOT NULL,
  prenom VARCHAR(100) NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  tel VARCHAR(15)
);

CREATE TABLE Consultations (
   id_consultation INT PRIMARY KEY AUTO_INCREMENT,
   date_consultation DATE NOT NULL,
   id_patient INT NOT NULL,
   id_medecin INT NOT NULL,
   FOREIGN KEY (id_patient) REFERENCES Patients(id_patient) ON DELETE CASCADE,
   FOREIGN KEY (id_medecin) REFERENCES Medecins(id_medecin) ON DELETE CASCADE
);
