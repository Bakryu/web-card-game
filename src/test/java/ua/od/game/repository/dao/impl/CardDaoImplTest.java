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
            System.out.println(card.get(i).getName());

        }

        }



    }



