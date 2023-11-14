<?php

namespace App\Entity;
use App\Entity\Commande;

use Doctrine\ORM\Mapping as ORM;
use App\Repository\LineorderRepository;


#[ORM\Entity(repositoryClass:LineorderRepository::class)]
class Lineorder
{
   
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $idO= null;

  
    #[ORM\Column(length:50)]
    private ?string $productname=null;

  
    #[ORM\Column]
    private ? int $quantite=null;

  
    #[ORM\Column]
    private ?float $prix=null;

    
    #[ORM\ManyToOne(targetEntity: Commande::class,inversedBy:'Lineorder')]
    #[ORM\JoinColumn(name: 'id_c', referencedColumnName: 'id_c')]

    private ?Commande $id_c;


    public function getIdO(): ?int
    {
        return $this->idO;
    }

    public function getProductname(): ?string
    {
        return $this->productname;
    }

    public function setProductname(string $productname): static
    {
        $this->productname = $productname;

        return $this;
    }

    public function getQuantite(): ?int
    {
        return $this->quantite;
    }

    public function setQuantite(int $quantite): static
    {
        $this->quantite = $quantite;

        return $this;
    }

    public function getPrix(): ?float
    {
        return $this->prix;
    }

    public function setPrix(float $prix): static
    {
        $this->prix = $prix;

        return $this;
    }

    public function getId_c(): ?Commande
    {
        return $this->id_c;
    }

    public function setId_c(Commande $id_c): static
    {
        $this->id_c = $id_c;

        return $this;
    }

   


}