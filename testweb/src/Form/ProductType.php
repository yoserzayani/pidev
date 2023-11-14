<?php

namespace App\Form;

use App\Entity\Product;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\NumberType;

class ProductType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('nom',null, [
                'attr' => ['placeholder' => 'Name'],
                'label' => false,])
            ->add('prix',null, [
                'attr' => ['placeholder' => 'Price'],'label' => false,])
            ->add('qte', NumberType::class, [
                'attr' => ['placeholder' => 'quantity'],
                'html5' => true,
                'attr' => ['min' => 1, 'max' => 100],'label' => false,
            ])
            ->add('categ', ChoiceType::class, [
                'placeholder' => 'Select a category', // Optional placeholder text
                'choices' => [
                    'Kitshen' => 'kitshen',
                    'Tapestry' => 'tapestry',
                    'Jewelry' => 'jewelry',
                    'Pottery' => 'pottery',
                ],'label' => false,])
            ->add('matiere',null, [
                'attr' => ['placeholder' => 'Material'],'label' => false,])
            ->add('description',null, [
                'attr' => ['placeholder' => 'Description'],'label' => false,])
            ->add('image',FileType::class,[
                'mapped'=>false,
                'label' => false,])
            ->add('Save',SubmitType::class)
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Product::class,
        ]);
    }
}
