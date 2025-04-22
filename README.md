# Dishcovery

Dishcovery is an **Android** application built with **Jetpack Compose** and **Kotlin**, following **Clean & Modular Architecture** principles.  
It integrates the **[Spoonacular Food API](https://spoonacular.com/food-api)** so users can discover and search for recipes.  
The project is engineered for **scalability, testability, and maintainability**, featuring:

- Multiple build variants (Debug/Release)
- Pagination with Paging 3
- Dependency‑injection via **Hilt**
- Dependency management through a **Gradle Version Catalog**

---

## Table of Contents
- [Features](#features)
- [Coming Soon](#coming-soon)
- [Architecture](#architecture)
  - [Core Modules](#core-modules)
  - [Modular Structure & Feature Breakdown](#modular-structure--feature-breakdown)
  - [Clean Architecture Structure](#clean-architecture-structure)
  - [Presentation Patterns](#presentation-patterns)
  - [Design Trade‑offs](#design-trade-offs)
- [Environment & Build Configuration](#environment--build-configuration)
- [API Integration](#api-integration)
- [Testing](#testing)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)
- [Acknowledgments](#acknowledgments)

---

## Features
- **Discover Recipes** – Browse recipes powered by the Spoonacular API.  
- **Search Recipes** – Search by name with infinite scroll and pull‑to‑refresh (Paging 3).  
- **Pagination & Refresh** – Seamless load‑more using `LazyPagingItems` and `SwipeRefresh`.  
- **Multi‑Environment Support** – Debug/Release build types with secure API keys.  
- **Internationalization** – Centralized string resources for easy localization.  
- **Accessibility** – Leverages Compose’s built‑in accessibility features.  
- **Dependency Injection** – Hilt for compile‑time DI.  
- **Clean & Modular Architecture** – Core/feature modules with clear boundaries.  
- **Robust Build System**  
  - Gradle Version Catalog for dependency BOM  
  - KSP code‑gen for faster builds  
- **Type‑Safe Pagination** – Defensive mapping to avoid `ClassCastException`.

---

## Coming Soon
- **Offline Caching** – Room/SQLite in `data:database`.  
- **Analytics** – Firebase Analytics in `toolkit:analytics`.  
- **Crash Reporting** – Firebase Crashlytics or Sentry.  
- **Remote Config / A‑B Tests** – Firebase Remote Config.  
- **CI/CD** – GitHub Actions or Jenkins.  
- **MVI Pattern** – Evaluate MVI alternative to MVVM.  
- **Unit & UI Tests** – JUnit, Espresso, Compose Testing.

---

## Architecture

Dishcovery implements *Clean Architecture* to enforce separation of concerns:

### Core Modules
| Module | Responsibility |
|--------|----------------|
| **domain** | Kotlin data models & use‑cases |
| **data:network** | Retrofit + Spoonacular API client |
| **data:database** | *(planned)* Room cache |
| **toolkit:logger** | Timber wrapper |
| **toolkit:analytics** | *(planned)* analytics |
| **uikit** | Reusable Compose components & resources |

### Modular Structure & Feature Breakdown
```
app
└── feature:recipe
    ├── presentation (Compose UI)
    ├── domain (use‑cases)
    └── data (repository impl)
```

### Clean Architecture Structure
- **Domain** – Pure Kotlin models & use‑cases.
- **Data** – Repositories + DTO ↔︎ model mappers.
- **Presentation** – Compose UI + ViewModels.

### Presentation Patterns
- Current: **MVVM** with Hilt‑injected ViewModels.  
- Future: **MVI** exploration for complex state.

### Design Trade‑offs
| Decision | Pros | Cons |
|----------|------|------|
| Clean Arch | Testable, modular | Extra boilerplate |
| Jetpack Compose | Declarative, accessible UI | Requires careful state mgmt |
| Paging 3 | Handles pagination efficiently | `items(count)` workaround |

---

## Environment & Build Configuration
| Tool | Version |
|------|---------|
| **Gradle** | 8.7 |
| **AGP** | 8.9.1 |
| **Kotlin** | 2.0.21 |
| **KSP** | 2.0.21‑1.0.25 |

- Build variants:
  - **Debug** – `spoonacularApiKeyDebug`  
  - **Release** – `spoonacularApiKeyRelease`
- Dependencies declared in `libs.versions.toml` (e.g. `paging = "3.3.6"`).

---

## API Integration
```properties
# gradle.properties
spoonacularApiKeyDebug=your_debug_api_key
spoonacularApiKeyRelease=your_release_api_key
```

`RecipeRepositoryImpl` (in **data:network**) calls the API via Retrofit and maps DTOs to `RecipeModel`.  
Planned `data:database` module will add cache‑first logic for offline use.

---

## Testing
### Unit Tests
- **Use‑cases** – business rules  
- **Repositories** – API <-> model mapping  
- **ViewModels** – state + pagination

### UI Tests
- **Compose Testing** for screens & flows  
- **Espresso** for navigation & intent validation

### Variant Tests
- Run tests in Debug & Release flavours to validate key separation.

---

## Usage
1. **Clone** the repo and open in Android Studio.  
2. **Add** your API keys to `gradle.properties`.  
3. **Sync** Gradle, then **Run** on an emulator/device.  
4. Search for recipes in **Home**, scroll to paginate, pull‑to‑refresh, and switch tabs via bottom nav.

---

## Contributing
1. Fork → `git checkout -b feature/YourFeatureName`  
2. Commit → `git push origin feature/YourFeatureName`  
3. Open a **Pull Request** with a clear description & linked issues.

---

## License
This project is released under the **MIT License**.  
See the [LICENSE](LICENSE) file for full details.

---

## Acknowledgments
- Spoonacular Food API  
- Jetpack Compose & Android Dev Community  
- Retrofit • Hilt • Paging 3 • Timber  

**Happy cooking & happy coding!**
