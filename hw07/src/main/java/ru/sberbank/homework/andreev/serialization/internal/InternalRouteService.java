package ru.sberbank.homework.andreev.serialization.internal;

import ru.sberbank.homework.common.CachePathProvider;
import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;
import ru.sberbank.homework.common.RouteService;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class InternalRouteService extends RouteService<City, Route<City>> {

    public InternalRouteService(CachePathProvider pathProvider) {
        super(pathProvider, false);
    }

    @Override
    public Route<City> getRoute(String from, String to) {
        String key = from + "_" + to;
        InternalRoute route = null;

        try (FileInputStream fis = new FileInputStream(pathProvider.getCacheDirectoryPath() + File.separator + key)) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            route = (InternalRoute) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
        }

        if (route == null) {
            route = (InternalRoute) super.getRoute(from, to);
            try (FileOutputStream fos = new FileOutputStream(pathProvider.getCacheDirectoryPath() + File.separator + key)) {
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(route);
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return route;
    }

    @Override
    protected City createCity(int id, String cityName, LocalDate foundDate, long numberOfInhabitants) {
        return new City(id, cityName, foundDate, numberOfInhabitants);
    }

    @Override
    protected Route<City> createRoute(List<City> cities) {
        return new InternalRoute(UUID.randomUUID().toString(), cities);
    }
}