<?php

namespace App\Controller;

use App\Repository\ProductRepository;
use PhpParser\Node\Name;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class BoutiqueController extends AbstractController
{
    #[Route('/boutique', name: 'app_boutique')]
    public function index(): Response
    {
        return $this->render('boutique/index.html.twig', [
            'controller_name' => 'BoutiqueController',
        ]);
    }
    #[Route('/shop' , name:"shopping" )]
    public function shop(ProductRepository $productRepository) {
        $product=$productRepository->findAll();
        return $this->render('boutique/shop.html.twig' ,[
            'produit'=>$product,
        ]);
    }
}
