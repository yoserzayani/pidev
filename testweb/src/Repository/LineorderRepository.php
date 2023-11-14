<?php

namespace App\Repository;

use App\Entity\Lineorder;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @extends ServiceEntityRepository<Lineorder>
 *
 * @method Lineorder|null find($id, $lockMode = null, $lockVersion = null)
 * @method Lineorder|null findOneBy(array $criteria, array $orderBy = null)
 * @method Lineorder[]    findAll()
 * @method Lineorder[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class LineorderRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Lineorder::class);
    }

//    /**
//     * @return Lineorder[] Returns an array of Lineorder objects
//     */
//    public function findByExampleField($value): array
//    {
//        return $this->createQueryBuilder('l')
//            ->andWhere('l.exampleField = :val')
//            ->setParameter('val', $value)
//            ->orderBy('l.id', 'ASC')
//            ->setMaxResults(10)
//            ->getQuery()
//            ->getResult()
//        ;
//    }

//    public function findOneBySomeField($value): ?Lineorder
//    {
//        return $this->createQueryBuilder('l')
//            ->andWhere('l.exampleField = :val')
//            ->setParameter('val', $value)
//            ->getQuery()
//            ->getOneOrNullResult()
//        ;
//    }
}
