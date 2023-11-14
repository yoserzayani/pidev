<?php

namespace App\Entity;

use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
use App\Repository\ProductRepository;

#[UniqueEntity(fields: 'nom', message: 'Ce nom de produit est déjà utilisé.')]
#[ORM\Entity(repositoryClass:ProductRepository::class)]
class Product
{
  
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]

    private ? int $idpdts=null;

   
    #[Assert\NotBlank]
    #[Assert\Length(max:50)]
    #[Assert\NotBlank(message:"Name can't be empty")]
    #[ORM\Column(length:50, unique:true)]
    private ? string $nom=null;
    
    #[Assert\NotBlank(message:"price can't be empty")]
    #[ORM\Column]
    private ?float $prix=null;

    #[Assert\NotBlank(message:"Quantity can't be empty")]
    #[ORM\Column]
    private ?int $qte=null;

    #[Assert\NotBlank(message:"Category can't be empty")]
    #[ORM\Column(length:50)]
    private ?String $categ=null;

    
    #[Assert\NotBlank(message:"Metrial can't be empty")]
    #[ORM\Column(length:50)]
    private ? string $matiere=null;

    #[Assert\NotBlank(message:"Description can't be empty")]
    #[ORM\Column(length:350)]
    private ?string $description=null;


   // #[Assert\NotBlank(message:"Picture can't be empty")]
    #[ORM\Column(length:1000)]
    private ?string $image=null;


    public function getIdpdts(): ?int
    {
        return $this->idpdts;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    public function getPrix(): ?float
    {
        return $this->prix;
    }

    public function setPrix(float $prix): self
    {
        $this->prix = $prix;

        return $this;
    }

    public function getQte(): ?int
    {
        return $this->qte;
    }

    public function setQte(int $qte): self
    {
        $this->qte = $qte;

        return $this;
    }

    public function getCateg(): ?string
    {
        return $this->categ;
    }

    public function setCateg(string $categ): self
    {
        $this->categ = $categ;

        return $this;
    }

    public function getMatiere(): ?string
    {
        return $this->matiere;
    }

    public function setMatiere(string $matiere): self
    {
        $this->matiere = $matiere;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(string $description): self
    {
        $this->description = $description;

        return $this;
    }

    public function getImage(): ?string
    {
        return $this->image;
    }

    public function setImage(string $image): self
    {
        $this->image = $image;

        return $this;
    }

  


}