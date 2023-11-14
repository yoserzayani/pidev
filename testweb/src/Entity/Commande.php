<?php

namespace App\Entity;

use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;
use App\Repository\CommandeRepository;



#[ORM\Entity(repositoryClass:CommandeRepository::class)]
class Commande
{
    
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
 
    private ? int $idC=null;

    
    #[ORM\Column(length:50)]
    private ? string $nomc=null;

   
    #[ORM\Column]
    private ?int $idClient=null;

   
    #[ORM\Column(length:50)]
    private ?string $adresse=null;

   
    #[ORM\Column]
    private ? \DateTime $date=null ;

    
    #[ORM\Column]
    private ?int $numtel=null;

    #[ORM\Column(length:50)]
    private ? string $email=null;

   
    #[ORM\Column(length:50)]
    private ? float $total=null;


    public function getIdC(): ?string
    {
        return $this->idC;
    }

    public function getNomc(): ?string
    {
        return $this->nomc;
    }

    public function setNomc(string $nomc): static
    {
        $this->nomc = $nomc;

        return $this;
    }

    public function getIdClient(): ?int
    {
        return $this->idClient;
    }

    public function setIdClient(int $idClient): static
    {
        $this->idClient = $idClient;

        return $this;
    }

    public function getAdresse(): ?string
    {
        return $this->adresse;
    }

    public function setAdresse(string $adresse): static
    {
        $this->adresse = $adresse;

        return $this;
    }

    public function getDate(): ?\DateTimeInterface
    {
        return $this->date;
    }

    public function setDate(\DateTimeInterface $date): static
    {
        $this->date = $date;

        return $this;
    }

    public function getNumtel(): ?int
    {
        return $this->numtel;
    }

    public function setNumtel(int $numtel): static
    {
        $this->numtel = $numtel;

        return $this;
    }

    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(string $email): static
    {
        $this->email = $email;

        return $this;
    }

    public function getTotal(): ?float
    {
        return $this->total;
    }

    public function setTotal(float $total): static
    {
        $this->total = $total;

        return $this;
    }


}
