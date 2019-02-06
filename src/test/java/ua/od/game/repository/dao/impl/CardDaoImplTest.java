package ua.od.game.repository.dao.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.od.game.model.CardEntity;
import ua.od.game.repository.dao.DbTest;

import java.util.List;

import static org.junit.Assert.*;

public class CardDaoImplTest extends DbTest {
    CardDaoImpl cardDao;

   @Before
  public void init(){cardDao = new CardDaoImpl();}

    @Test
    public void getCardsListTest(){

List<CardEntity> card = cardDao.getAllCardList();
        System.out.println(card.size());
        for (int i = 0; i <card.size(); i++) {
            System.out.println(card.get(i).getId());
            System.out.println(card.get(i).getName());
            System.out.println(card.get(i).getDescription());
            System.out.println(card.get(i).getCardGroup().getName());
            System.out.println(card.get(i).getCardGroup().getId());
            System.out.println(card.get(i).getPalayerResourceSetList());
            System.out.println(card.get(i).getPalayerBuildingSetList());
            System.out.println(card.get(i).getEnemyUpgradeSetList());
            System.out.println(card.get(i).getEnemyBuildingSetList());
        }

        }



    }



