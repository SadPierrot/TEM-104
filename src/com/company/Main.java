package com.company;

import com.company.comand.CommandDeviceCounter;
import com.company.dto.DeviceCounterDto;
import com.company.dto.ResponseDeviceDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final ArrayList<ResponseDeviceDTO> devicesResponses = new ArrayList<>();

    private static final ArrayList<DeviceCounterDto> devicesDto = new ArrayList<>() {{
        add(new DeviceCounterDto(1, List.of(CommandDeviceCounter.PROFILE_POWER_FLASH_512K, CommandDeviceCounter.PROFILE_POWER_128
        ), 3));
    }};

    public static void main(String[] args) throws IOException {
        DeviceConverter deviceConverter = null;

        try {
            DataOutput data = new DataOutput();
            deviceConverter = new DeviceConverter("195.18.36.153", 4002);
            ConnectionChannel connectionChannel = deviceConverter.getChannel();

            for (DeviceCounterDto counterDto : devicesDto) {
                CounterDevice counterDevice = new CounterDevice(counterDto, connectionChannel);
                System.out.println("Счётчик: №" + counterDto.getNumberCounter());
                devicesResponses.add(counterDevice.runInstruction());
                System.out.println();
            }
            data.takeResult(devicesResponses);
        } catch (Exception e) {
            System.out.println("Исключение: " + e);
        } finally {
            if (deviceConverter != null) {
                deviceConverter.socket.close();
                if (deviceConverter.socket.isClosed()) {
                    System.out.println("Сокет был закрыт");
                } else {
                    System.out.println("Сокет не был закрыт");
                }
            }
        }
    }
}
