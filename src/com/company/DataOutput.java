package com.company;

import com.company.comand.CommandDeviceCounter;
import com.company.dto.ResponseDeviceDTO;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DataOutput {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy");

    public void takeResult(ArrayList<ResponseDeviceDTO> responseDTO) {
        for (ResponseDeviceDTO dto : responseDTO) {
            System.out.println("Ответ счётчика: №" + dto.getNumberCounter());
            for (int j = 0; j < dto.getCommandsCounterPrint().size(); j++) {
                CommandDeviceCounter commandCounter = dto.getCommandsCounterPrint().get(j);
                System.out.println("ANSWER " + dto.getCommandsCounterPrint().get(j).getTitle());
                if (commandCounter == CommandDeviceCounter.PROFILE_POWER_128) {
                    System.out.println("DateTime:  " + dto.getProfilePower128Dto().getLocalDateTime().format(formatter));
                    System.out.println();
                }
                if (commandCounter == CommandDeviceCounter.PROFILE_POWER_2000) {
                    System.out.println("Число систем:                                    " + dto.getProfilePower2000Dto().getSystems());
                    System.out.println("Тип датчиков расхода                             " + dto.getProfilePower2000Dto().getType_g());
                    System.out.println("Тип температур в статистике:                     " + dto.getProfilePower2000Dto().getType_t());
                    System.out.println("Номер прибора в сети                             " + dto.getProfilePower2000Dto().getNet_num());
                    System.out.println("Заводской номер прибора                          " + dto.getProfilePower2000Dto().getNumber());
                    System.out.println("Диаметр условного прохода по каналам             " + dto.getProfilePower2000Dto().getDiam());
                    System.out.println("Максимальное значение расхода по системам(Gmax1) " + dto.getProfilePower2000Dto().getG_max());
                    System.out.println("Установленное значение Gуmax в процентах от (*)  " + dto.getProfilePower2000Dto().getG_pcnt_max()+ "%");
                    System.out.println("Установленное значение Gуmin в процентах от (*)  " + dto.getProfilePower2000Dto().getG_pcnt_min()+ "%");
                    System.out.println("Максимальная частота                             " + dto.getProfilePower2000Dto().getF_max() + " Гц");
                    System.out.println("Вес импульса                                     " + dto.getProfilePower2000Dto().getWeight());
                    System.out.println("Адрес следующей часовой записи                   " + dto.getProfilePower2000Dto().getNext_hour());
                    System.out.println("Адрес следующей суточной записи                  " + dto.getProfilePower2000Dto().getNext_day());
                    System.out.println("Адрес следующей записи на отчетную дату          " + dto.getProfilePower2000Dto().getNext_month());
                    System.out.println();
                }


                if (commandCounter == CommandDeviceCounter.PROFILE_POWER_FLASH_512K) {
                    System.out.println("Время и дата последняя запись: " + dto.getProfilePowerFlash().getTime().format(formatter));
                    System.out.println();
                }

                if (commandCounter == CommandDeviceCounter.RAM) {

                }
            }
            System.out.println();
        }
    }
}