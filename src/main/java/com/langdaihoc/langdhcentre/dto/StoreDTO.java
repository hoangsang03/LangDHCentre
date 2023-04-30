package com.langdaihoc.langdhcentre.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreDTO {
    private boolean isOpening;

    private boolean isStarted = false;
    private boolean isShutdown = false;
    private boolean isHidden = false;
    private boolean isAutoOpenSetting = false;


}
