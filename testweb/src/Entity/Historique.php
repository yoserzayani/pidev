<?php

namespace App\Entity;

use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;
use DateTime;
use App\Repository\HistoriqueRepository;



#[ORM\Entity(repositoryClass:HistoriqueRepository::class)]
class Historique
{
    
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ? int $idh=null;

    #[ORM\ManyToOne(targetEntity: Commande::class,inversedBy:'historique')]
    #[ORM\JoinColumn(name: 'id_c', referencedColumnName: 'id_c')]
    private ? Commande $numC=null;

   
    #[ORM\Column]
    private ?DateTime $dateorder=null;

     
    #[ORM\Column(length:50)]
    private ? string $product=null;

    
    #[ORM\Column]
    private ? float $prix=null;

    #[ORM\Column]
    private ?int $quantite=null;

   

    public function getIdh(): ?int
    {
        return $this->idh;
    }

    public function getNumC(): ?Commande
    {
        return $this->numC;
    }

    public function setNumc(Commande $numC): static
    {
        $this->numC = $numC;

        return $this;
    }

    public function getDateorder(): ?\DateTimeInterface
    {
        return $this->dateorder;
    }

    public function setDateorder(\DateTimeInterface $dateorder): static
    {
        $this->dateorder = $dateorder;

        return $this;
    }

    public function getProduct(): ?string
    {
        return $this->product;
    }

    public function setProduct(string $product): static
    {
        $this->product = $product;

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

    public function getQuantite(): ?int
    {
        return $this->quantite;
    }

    public function setQuantite(int $quantite): static
    {
        $this->quantite = $quantite;

        return $this;
    }


}
