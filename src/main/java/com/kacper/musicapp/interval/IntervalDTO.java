package com.kacper.musicapp.interval;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IntervalDTO
{
    private Integer id;
    private String intervalName;
    private String firstNote;
    private String secondNote;
    private String difficulty;
}
