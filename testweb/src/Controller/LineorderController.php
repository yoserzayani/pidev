<?php

namespace App\Controller;

use App\Entity\Lineorder;
use App\Form\LineorderType;
use App\Entity\Product;
use App\Repository\ProductRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Component\HttpFoundation\Session\SessionInterface;

class LineorderController extends AbstractController
{
    #[Route('/lineorder', name: 'app_lineorder')]
    public function index(): Response
    {
        return $this->render('lineorder/index.html.twig', [
            'controller_name' => 'LineorderController',
        ]);
    }

    #[Route('/add-to-cart/{id}', name: 'add_to_cart')]
    public function addToCart(
        $id,
        ProductRepository $productRepository,
        SessionInterface $session
    ): Response {
        $product = $productRepository->find($id);

        // Add the product ID to the session-based cart
        $cart = $session->get('cart', []);
        $cart[] = $id;
        $session->set('cart', $cart);

        $this->addFlash('success', 'Product added to the cart successfully!');

        // Redirect back to the shop page
        return $this->redirectToRoute('shopping');
    }

    #[Route('/cart', name: 'view_cart')]
    public function viewCart(
        SessionInterface $session,
        ProductRepository $productRepository
    ): Response {
        // Retrieve the product IDs stored in the session
        $cart = $session->get('cart', []);

        // Retrieve the actual products from the database using product IDs
        $cartProducts = $productRepository->findBy(['idpdts' => $cart]);

        return $this->render('lineorder/getcart.html.twig', [
            'cartProducts' => $cartProducts,
        ]);
    }

    #[Route('/checkout', name: 'checkout')]
    public function checkout(
        EntityManagerInterface $entityManager,
        SessionInterface $session,
        ProductRepository $productRepository
    ): Response {
        // Retrieve the product IDs stored in the session
        $cart = $session->get('cart', []);

        // Retrieve the actual products from the database using product IDs
        $cartProducts = $productRepository->findBy(['idpdts' => $cart]);

        // Create Lineorder entities and persist them
        foreach ($cartProducts as $product) {
            $lineorder = new Lineorder();
            $lineorder->setProductname($product->getNom());
            $lineorder->setPrix($product->getPrix());
            $lineorder->setQuantite(1);

            $entityManager->persist($lineorder);
        }

        // Flush the changes to the database
        $entityManager->flush();

        // Clear the session-based cart after checkout
        $session->remove('cart');

        $this->addFlash('success', 'Checkout successful!');

        // Redirect to a success page or back to the shop
        return $this->redirectToRoute('shopping');
    }
}