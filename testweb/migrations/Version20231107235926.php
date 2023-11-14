<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20231107235926 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE commande (id_c INT AUTO_INCREMENT NOT NULL, nomc VARCHAR(50) NOT NULL, id_client INT NOT NULL, adresse VARCHAR(50) NOT NULL, date DATETIME NOT NULL, numtel INT NOT NULL, email VARCHAR(50) NOT NULL, total DOUBLE PRECISION NOT NULL, PRIMARY KEY(id_c)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE historique (idh INT AUTO_INCREMENT NOT NULL, id_c INT DEFAULT NULL, dateorder DATETIME NOT NULL, product VARCHAR(50) NOT NULL, prix DOUBLE PRECISION NOT NULL, quantite INT NOT NULL, INDEX IDX_EDBFD5ECC12C7510 (id_c), PRIMARY KEY(idh)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE lineorder (id_o INT AUTO_INCREMENT NOT NULL, id_c INT DEFAULT NULL, productname VARCHAR(50) NOT NULL, quantite INT NOT NULL, prix DOUBLE PRECISION NOT NULL, INDEX IDX_2341105AC12C7510 (id_c), PRIMARY KEY(id_o)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE product (idpdts INT AUTO_INCREMENT NOT NULL, nom VARCHAR(50) NOT NULL, prix DOUBLE PRECISION NOT NULL, qte INT NOT NULL, categ VARCHAR(50) NOT NULL, matiere VARCHAR(50) NOT NULL, description VARCHAR(350) NOT NULL, image VARCHAR(1000) NOT NULL, UNIQUE INDEX UNIQ_D34A04AD6C6E55B5 (nom), PRIMARY KEY(idpdts)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE messenger_messages (id BIGINT AUTO_INCREMENT NOT NULL, body LONGTEXT NOT NULL, headers LONGTEXT NOT NULL, queue_name VARCHAR(190) NOT NULL, created_at DATETIME NOT NULL, available_at DATETIME NOT NULL, delivered_at DATETIME DEFAULT NULL, INDEX IDX_75EA56E0FB7336F0 (queue_name), INDEX IDX_75EA56E0E3BD61CE (available_at), INDEX IDX_75EA56E016BA31DB (delivered_at), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE historique ADD CONSTRAINT FK_EDBFD5ECC12C7510 FOREIGN KEY (id_c) REFERENCES commande (id_c)');
        $this->addSql('ALTER TABLE lineorder ADD CONSTRAINT FK_2341105AC12C7510 FOREIGN KEY (id_c) REFERENCES commande (id_c)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE historique DROP FOREIGN KEY FK_EDBFD5ECC12C7510');
        $this->addSql('ALTER TABLE lineorder DROP FOREIGN KEY FK_2341105AC12C7510');
        $this->addSql('DROP TABLE commande');
        $this->addSql('DROP TABLE historique');
        $this->addSql('DROP TABLE lineorder');
        $this->addSql('DROP TABLE product');
        $this->addSql('DROP TABLE messenger_messages');
    }
}
