package ua.od.game.repository.dao.impl;

import org.junit.Before;
import org.junit.Test;
import ua.od.game.model.BuildingEntity;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import ua.od.game.model.ResourceSetEntity;
import ua.od.game.repository.dao.DbTest;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class BuildingDaoImplTest extends DbTest {

    BuildingDaoImpl buildingDao;
    private Integer resource_set_id;

    @Parameters()
    public static Collection b() {
        return Arrays.asList(new Integer[]{1, 5, 10, 11});
    }

    @Before
    public void init() {
        buildingDao = new BuildingDaoImpl();
    }

    public BuildingDaoImplTest(Integer i) {
        this.resource_set_id = i;
    }

    @Test
    public void getAllResourceSetTest() {
        List<ResourceSetEntity> rsList = buildingDao.getAllResourceSet();
        for (int i = 0; i < rsList.size(); i++) {
            System.out.print(rsList.get(i).getId() + " ");
            System.out.print(rsList.get(i).getSetId() + " ");
            System.out.print(rsList.get(i).getResourceId()+ " ");
            System.out.print(rsList.get(i).getAmount()+ " ");
        }
        assertFalse(rsList.isEmpty());
    }

    @Test
    public void getAllBuildingResourceSetListTest() {
        List<ResourceSetEntity> rsList = buildingDao.getAllResourceSet();
        List<ResourceSetEntity> rSet = buildingDao.getAllBuildingResourceSetList(resource_set_id, rsList);
        for (int i = 0; i < rSet.size(); i++) {
            System.out.print(rSet.get(i).getId() + " ");
            System.out.print(rSet.get(i).getSetId() + " ");
            System.out.print(rSet.get(i).getResourceId()+ " ");
            System.out.print(rSet.get(i).getAmount()+ " \n");
        }
        assertFalse(rsList.isEmpty());
    }

    @Test
    public void getAllBuildingListTest() {
        List<BuildingEntity> be = buildingDao.getAllBuildingList();
        for (int i = 0; i < be.size(); i++) {
            System.out.print(be.get(i).getId() + " ");
            System.out.print(be.get(i).getName() + " ");
            System.out.print(be.get(i).getDescription()+ " ");
            System.out.print(be.get(i).getDefaultNumber()+ " || ");
            be.get(i).getResourceSetList().forEach(
                    resourceSetEntity -> System.out.print(resourceSetEntity.getResourceId() + " "));
            System.out.println();
        }
        assertFalse(be.isEmpty());
    }





}
