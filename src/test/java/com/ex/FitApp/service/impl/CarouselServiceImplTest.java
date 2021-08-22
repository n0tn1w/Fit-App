package com.ex.FitApp.service.impl;

import com.ex.FitApp.services.ImageShuffler;
import com.ex.FitApp.services.impl.CarouselServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

public class CarouselServiceImplTest {

  private CarouselServiceImpl serviceToTest;

  @BeforeEach
  public void setUp() {
    serviceToTest = new CarouselServiceImpl(
        List.of("A", "B", "C"),
        new TestImageShuffler()
    );
  }

  @Test
  public void testRefresh() {
    serviceToTest.refresh();

    Assertions.assertEquals("C", serviceToTest.firstImage());
    Assertions.assertEquals("B", serviceToTest.secondImage());
    Assertions.assertEquals("A", serviceToTest.thirdImage());
  }

  static class TestImageShuffler implements ImageShuffler {
    @Override
    public void shuffle(List<String> images) {
      Collections.reverse(images);
    }
  }

}
