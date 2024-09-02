package com.example.genshinpickerspring.Controllers;

import com.example.genshinpickerspring.Models.ElementType;
import com.example.genshinpickerspring.Models.Hero;
import com.example.genshinpickerspring.Models.RequestModels.TournamentHeroInfo;
import com.example.genshinpickerspring.Services.HeroService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/v1/heroes")
public class HeroController {
    @Autowired
    private HeroService heroService;

    @Value("${static.resource.directory}")
    private String staticResourceDirectory;

    @Value("${heroes.image.upload.directory}")
    private String uploadDirectory;


    //HeroInfo
    @GetMapping("/heroTournamentInfo")
    public List<TournamentHeroInfo> getAllHeroesForTournament() {
        return heroService.convertToTournamentHeroInfoModel(heroService.getAll());
    }

    @GetMapping
    public List<Hero> getAllHeroes() {
        return heroService.getAll();
    }

    @GetMapping("/photo/{id}")
    public ResponseEntity<Resource> getHeroPath(@PathVariable Long id) throws MalformedURLException {
        Hero hero = heroService.getHeroById(id).orElseThrow();

        Path filePath = Paths.get(hero.getPhotoPath()).normalize();
        try {
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                throw new ResourceNotFoundException("File not found " + hero.getPhotoPath());
            }
        } catch (Exception e) {
            throw new ResourceNotFoundException("File not found " + hero.getPhotoPath(), e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hero> getHeroById(@PathVariable Long id) {
        return heroService.getHeroById(id)
                .map(Hero -> ResponseEntity.ok().body(Hero))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<String> createHero(@RequestParam("name") String name,
                                             @RequestParam("starCount") Integer starCount,
                                             @RequestParam("elementType") String elementTypeString,
                                             @RequestParam("file") MultipartFile file) {

        if (file.isEmpty() || !isImageFile(file)) {
            return new ResponseEntity<>("Invalid file. Please upload an image file.", HttpStatus.BAD_REQUEST);
        }

        try {
            // Convert string to ElementType
            ElementType elementType;
            try {
                elementType = ElementType.valueOf(elementTypeString);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>("Invalid element type.", HttpStatus.BAD_REQUEST);
            }

            // Resolve the relative path and create directories if they do not exist
            Path uploadPath = Paths.get(staticResourceDirectory, uploadDirectory).toAbsolutePath().normalize();
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Save the file
            String fileName = file.getOriginalFilename();
            assert fileName != null;
            Path filePath = uploadPath.resolve(fileName);
            file.transferTo(filePath.toFile());

            // Create and save the hero
            Hero hero = new Hero();
            hero.setName(name);
            hero.setStarCount(starCount);
            hero.setElementType(elementType);
            hero.setPhotoPath(filePath.toString());
            heroService.addHero(hero);

            return new ResponseEntity<>("Hero created successfully with image path: " + filePath.toString(), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Error uploading image: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean isImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && (
                contentType.equals("image/webp") ||
                contentType.equals("image/jpeg") || contentType.equals("image/png") || contentType.equals("image/gif"));
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<String> handleMultipartException(MultipartException e) {
        return new ResponseEntity<>("Current request is not a multipart request", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hero> updateHero(@PathVariable Long id, @RequestBody Hero HeroDetails) {
        return ResponseEntity.ok(heroService.updateHero(id, HeroDetails));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteHero(@PathVariable Long id) {
        heroService.deleteHero(id);
        return ResponseEntity.noContent().build();
    }

    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }

        public ResourceNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
