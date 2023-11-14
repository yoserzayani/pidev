<?php

namespace App\Form;

use App\Entity\Lineorder;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\IntegerType;

class LineorderType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
          
        ->add('quantite', IntegerType::class, [
            'label' => 'Quantity',
            'attr' => ['min' => 1],
        ])
        ->add('submit', SubmitType::class, [
            'label' => 'Add to Basket',
        ]);
}
    

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Lineorder::class,
        ]);
    }
}
